package br.com.i7solution.integradornuubes.services;

import br.com.i7solution.integradornuubes.apinuubes.CustomerClient;
import br.com.i7solution.integradornuubes.apinuubes.OccurrenceClient;
import br.com.i7solution.integradornuubes.apinuubes.dtos.CustomerDTO;
import br.com.i7solution.integradornuubes.apinuubes.dtos.OccurrenceEF2DTO;
import br.com.i7solution.integradornuubes.apinuubes.dtos.OccurrenceEFDTO;
import br.com.i7solution.integradornuubes.entities.CepClient;
import br.com.i7solution.integradornuubes.entities.ClienteClient;
import br.com.i7solution.integradornuubes.entities.PracaClient;
import br.com.i7solution.integradornuubes.entities.dtos.*;
import br.com.i7solution.integradornuubes.exceptions.CepException;
import br.com.i7solution.integradornuubes.tipos.TipoContato;
import br.com.i7solution.integradornuubes.tipos.TipoEndereco;
import br.com.i7solution.integradornuubes.tools.Ferramentas;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Log4j2
@Service
public class NuubesService {

    @Autowired
    private CustomerClient clienteNuubes;
    @Autowired
    private OccurrenceClient ocorrenciasNuubes;
    @Autowired
    private ClienteClient clienteWinthor;
    @Autowired
    private CepClient cepClient;
    @Autowired
    private PracaClient pracaClient;

//    @Async(value="taskNuubesWinthor")
//    @Scheduled(fixedRate = 10000, initialDelay = 10000)
//    public void teste() {
//        log.info("teste 1...");
//    }

    //@Async(value="taskNuubesWinthor")
    @Scheduled(fixedDelay = 30000, initialDelay = 10000)
    public void clientesNuubesWinthor() {
        String pontoErro = "";
        try {
            var dtHoje = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dtHoje);
            cal.add(Calendar.DATE, -30);
            var dtNova = cal.getTime();
            var dataIni = Ferramentas.dataFormatada(dtNova, "MM/dd/yyyy");
            var dataFim = Ferramentas.dataFormatada(dtHoje, "MM/dd/yyyy");

            log.info("[clientesNuubesWinthor] - Sincronizando cadastros de clientes \"Nuubes -> Winthor\"...");
            pontoErro = "buscando ocorrências";
            var ocorrencias = ocorrenciasNuubes.getOcorrenciasPorPeriodo(dataIni, dataFim);

            if (ocorrencias != null) {
                if (ocorrencias.length > 0) {
                    for (var c = 0; c < ocorrencias.length; c++) {
                        if (//ocorrencias[c].getStatus().equalsIgnoreCase("Cadastro aprovado")
                                ocorrencias[c].getStatus().equalsIgnoreCase("Cadastro aprovado")
                                        && ocorrencias[c].getTipo().toUpperCase().contains("CADASTRO CLIENTES NOVOS")) {
                            ClienteDTO cli;
                            String tipoFJ = "";
                            OccurrenceEFDTO[] cliEF;
                            OccurrenceEF2DTO[] cliEF2;

                            if (ocorrencias[c].getNumero() != null) {
                                pontoErro = "buscando Extra Fields";
                                cliEF = ocorrenciasNuubes.getOcorrenciasExtraFields(ocorrencias[c].getNumero());
                                cliEF2 = ocorrenciasNuubes.getOcorrenciasExtraFields2(ocorrencias[c].getNumero());

                                var cpfCnpj = Ferramentas.somenteNumeros(
                                        cliEF[0].getCpfCnpj()
                                                .replace(" ", "")
                                );
                                
                                if(cpfCnpj == null) {
                                    log.info("CPF/CNPJ vindo do cliEF ocorrências Nuubes NULL");
                                } else {
                                	log.info("CPF/CNPJ vindo do cliEF ocorrências Nuubes " + cpfCnpj);
                                }
                                
                                
                                
                                //log.info("[clientesNuubesWinthor] - Verificando se o cliente já existe...");
                                if (cpfCnpj.length() == 14) {
                                    pontoErro = "buscando cadastro no Winthor(PJ)";
                                    tipoFJ = "J";
                                } else {
                                    pontoErro = "buscando cadastro no Winthor(PF)";
                                    tipoFJ = "F";
                                }
                                log.info("[clientesNuubesWinthor]-------------------------------------------------------------------"
                                        + "\n  -> Verificando ocorrência: " + ocorrencias[c].getNumero()
                                        + "\n  -> Status: " + ocorrencias[c].getStatus()
                                        + "\n  -> Tipo: " + ocorrencias[c].getTipo()
                                        + "\n  -> CPF/CNPJ: " + Ferramentas.formatarCpfCnpj(cliEF[0].getCpfCnpj(), tipoFJ)
                                );
                                cli = clienteWinthor.getClientePorCpfCnpj(cpfCnpj);
                                if(cli == null) {
                                	throw new Exception("Cliente Winthor de CPF/CNPJ " + cpfCnpj + " não foi localizado.");
                                }

                                if ((cli.getId() == null) && (cli.getError() == null)) {
                                    try {
                                        log.info("[clientesNuubesWinthor] - Buscando dados necessários p/ pré-cadastro do cliente cpf/cnpj:" + cpfCnpj + " no Winthor");
                                        pontoErro = "preenchendo dados";
                                        cli = new ClienteDTO();
                                        cli.setCpfCnpj(cpfCnpj);
                                        cli.setCpfCnpjEntrega(cpfCnpj);
                                        cli.setDataNascimento(Ferramentas.stringToDate(cliEF[0].getDtNascimento(), "yyyy-MM-dd"));
                                        if (tipoFJ.equalsIgnoreCase("F")) {
                                            cli.setTipoPessoa("Física");
                                        } else {
                                            cli.setTipoPessoa("Jurídica");
                                        }
                                        
                                        
                                        log.warn("Nome antes de Ferramentas.removerAcentos() >>> " + cliEF[0].getRazaoSocial());
                                        var nome = Ferramentas.removerAcentos(cliEF[0].getRazaoSocial().trim().toUpperCase());
                                        log.warn("Nome apos de Ferramentas.removerAcentos() >>> " + nome);
                                        log.warn("Tamanho do Nome >>> " + nome.length());
                                        if (nome.length() > 60) {
                                            cli.setNome(nome.substring(0, 60));
                                        } else {
                                            cli.setNome(nome);
                                        }
                                        log.warn("Nome apos cli.setNome() >>> " + nome);

                                        
                                        var fantasia = Ferramentas.removerAcentos(cliEF[0].getFantasia().trim().toUpperCase());
                                        if (fantasia.length() > 40) {
                                            cli.setNomeFantasia(fantasia.substring(0, 40));
                                        } else {
                                            cli.setNomeFantasia(fantasia);
                                        }
                                        cli.setInscricaoEstadual(cliEF[0].getInscEstadual().trim());
                                        //cli.setDataCadastro(Date.from(Instant.now()));
                                        cli.setEmail(cliEF[0].getEmail());
                                        cli.setEmailNfe(cliEF[0].getEmail());
                                        cli.setEmailCobranca(cliEF[0].getEmail());
                                        cli.setEmailRecebedor(cliEF[0].getEmail());
                                        var telefone1 = Ferramentas.somenteNumeros(cliEF[0].getTelefone1());
                                        var telefone2 = Ferramentas.somenteNumeros(cliEF[0].getTelefone2());

                                        if (telefone1.length() > 12) {
                                            cli.setTelefoneFixo(telefone1.substring(0, 12));
                                            cli.setTelefoneCobranca(telefone1.substring(0, 12));
                                            cli.setTelefoneComercial(telefone1.substring(0, 12));
                                            cli.setTelefoneEntrega(telefone1.substring(0, 12));
                                        } else {
                                            cli.setTelefoneFixo(telefone1);
                                            cli.setTelefoneCobranca(telefone1);
                                            cli.setTelefoneComercial(telefone1);
                                            cli.setTelefoneEntrega(telefone1);
                                        }
                                        if (telefone2.length() > 12) {
                                            cli.setTelefoneCelular(telefone2.substring(0, 12));
                                        } else {
                                            cli.setTelefoneCelular(telefone2);
                                        }

                                        pontoErro = "preenchendo endereços";
                                        var endCom = new EnderecoDTO();
                                        var rua = Ferramentas.removerAcentos(cliEF[0].getRuaEndereco().trim().toUpperCase());
                                        if (rua.length() > 40) {
                                            endCom.setRua(rua.substring(1, 40));
                                        } else {
                                            endCom.setRua(rua);
                                        }
                                        endCom.setComplemento(Ferramentas.removerAcentos(cliEF[0].getObsEntrega().toUpperCase()));
                                        if (endCom.getComplemento().length() <= 40) {
                                            endCom.setPontoRefer(endCom.getComplemento());
                                        } else {
                                            endCom.setPontoRefer(endCom.getComplemento().substring(0, 40));
                                        }
                                        endCom.setBairro(Ferramentas.removerAcentos(cliEF[0].getBairro().toUpperCase()));
                                        endCom.setNumero(Ferramentas.removerAcentos(cliEF[0].getNrEndereco().toUpperCase()));
                                        var cidade = Ferramentas.removerAcentos(cliEF[0].getCidade().toUpperCase());
                                        if (cidade.trim().length() > 15) {
                                            endCom.setCidade(cidade.trim().substring(0, 15));
                                        } else {
                                            endCom.setCidade(cidade.trim());
                                        }
                                        var cep = Ferramentas.somenteNumeros(cliEF[0].getCep().trim());
                                        if (cep.length() > 8) {
                                            endCom.setCep(cep.substring(0, 8));
                                        } else {
                                            endCom.setCep(cep);
                                        }

                                        endCom.setTipoEndereco(TipoEndereco.Comercial);
                                        var uf = cliEF[0].getEstado().trim().toUpperCase();
                                        if (uf.length() > 2) {
                                            endCom.setUf(Ferramentas.abreviarEstado(uf));
                                        } else {
                                            endCom.setUf(uf);
                                        }

                                        endCom.setCodigoPais(1058L);

                                        pontoErro = "buscando dados do CEP";
                                        var cepViaCep = cepClient.getDadosCep(endCom.getCep());
                                        if (cepViaCep != null) {
                                            if (cepViaCep.getIbge() != null) {
                                                endCom.setCodigoIbge(Ferramentas.stringToLong(cepViaCep.getIbge()));
                                            } else {
                                                throw new CepException();
                                            }
                                        } else {
                                            throw new CepException();
                                        }

                                        var endEnt = new EnderecoDTO();
                                        endEnt.setRua(endCom.getRua());
                                        endEnt.setComplemento(endCom.getComplemento());
                                        endEnt.setPontoRefer(endCom.getPontoRefer());
                                        endEnt.setBairro(endCom.getBairro());
                                        endEnt.setNumero(endCom.getNumero());
                                        endEnt.setCidade(endCom.getCidade());
                                        endEnt.setCep(endCom.getCep());
                                        endEnt.setTipoEndereco(TipoEndereco.Entrega);
                                        endEnt.setUf(endCom.getUf());
                                        endEnt.setCodigoIbge(endCom.getCodigoIbge());
                                        endEnt.setCodigoPais(1058L);

                                        var endCob = new EnderecoDTO();
                                        endCob.setRua(endCom.getRua());
                                        endCob.setComplemento(endCom.getComplemento());
                                        endCob.setPontoRefer(endCom.getPontoRefer());
                                        endCob.setBairro(endCom.getBairro());
                                        endCob.setNumero(endCom.getNumero());
                                        endCob.setCidade(endCom.getCidade());
                                        endCob.setCep(endCom.getCep());
                                        endCob.setTipoEndereco(TipoEndereco.Cobranca);
                                        endCob.setUf(endCom.getUf());
                                        endCob.setCodigoIbge(endCom.getCodigoIbge());
                                        endCob.setCodigoPais(1058L);

                                        var listaEnd = new EnderecoDTO[]{endCom, endEnt, endCob};
                                        cli.setEnderecos(listaEnd);

                                        pontoErro = "buscando praça correspondente";
                                        var praca = new PracaDTO();
                                        if ((cepViaCep != null) && (cepViaCep.getLocalidade() != null) && (cepViaCep.getUf() != null)) {
                                            praca = pracaClient.getPraca(
                                                    Ferramentas.removerAcentos(cepViaCep.getLocalidade().toUpperCase()),
                                                    Ferramentas.removerAcentos(cepViaCep.getUf().toUpperCase()),
                                                    tipoFJ.toUpperCase()
                                            );
                                        } else {
                                            praca = pracaClient.getPraca(
                                                    Ferramentas.removerAcentos(endCom.getCidade().toUpperCase()),
                                                    Ferramentas.removerAcentos(endCom.getUf().toUpperCase()),
                                                    tipoFJ.toUpperCase()
                                            );
                                        }

                                        if ((praca != null) && (praca.getId() != null)) {
                                            cli.setPraca(praca);
                                            cli.setPracaCobranca(praca);
                                        } else {
                                            var pracaDefault = new PracaDTO();
                                            if (tipoFJ.equalsIgnoreCase("F")) {
                                                pracaDefault.setId("242");
                                                pracaDefault.setDescricao("DEFAULT");
                                            } else {
                                                pracaDefault.setId("1");
                                                pracaDefault.setDescricao("DEFAULT");
                                            }
                                            cli.setPraca(pracaDefault);
                                            cli.setPracaCobranca(pracaDefault);
                                            log.warn("[clientesNuubesWinthor] - Não foi possível buscar dados referentes a praça para a cidade "
                                                    + Ferramentas.removerAcentos(endCom.getCidade().toUpperCase()) + "/" + endCom.getUf().toUpperCase()
                                            );
                                        }

                                        pontoErro = "preenchendo dados fiscais";
                                        cli = dadosFiscais(cli);
                                        pontoErro = "preenchendo dados comerciais";
                                        cli = dadosComerciais(cli);

                                        pontoErro = "dados de contato";
                                        var contato = new ContatoDTO();
                                        contato.setNome(cliEF[0].getNomeCompleto().trim());
                                        contato.setRg(cliEF[0].getRg().trim() + "/" + cliEF[0].getOrgaoExp().trim());
                                        contato.setDataNascimento(Ferramentas.stringToDate(cliEF[0].getDtNascimento(), "dd/MM/yyyy"));
                                        contato.setCpfCnpj(cliEF[0].getCpf().trim());
                                        var enderecoContato = cliEF[0].getEndereco().trim() + "," + cliEF[0].getNrEndereco().trim() + "," + cliEF[0].getComplemento().trim();
                                        if (enderecoContato.trim().length() > 40) {
                                            contato.setEndereco(enderecoContato.trim().substring(0, 40));
                                        } else {
                                            contato.setEndereco(enderecoContato.trim());
                                        }

                                        contato.setBairro(cliEF[0].getBairro().trim());
                                        var cepContato = Ferramentas.somenteNumeros(cliEF2[0].getCep().trim());
                                        if (cepContato.length() > 9) {
                                            contato.setCep(cepContato.substring(0, 9));
                                        } else {
                                            contato.setCep(cepContato);
                                        }

                                        contato.setCidade(cliEF2[0].getCidade().trim());
                                        contato.setTipoContato(TipoContato.Socio);
                                        if (cliEF2[0].getEstado().trim().length() > 2) {
                                            contato.setEstado(Ferramentas.abreviarEstado(cliEF2[0].getEstado().trim().toUpperCase()));
                                        } else {
                                            contato.setEstado(cliEF2[0].getEstado().trim().toUpperCase());
                                        }

                                        contato.setTelefone(cliEF2[0].getTelefone1());
                                        contato.setCelular(cliEF2[0].getTelefone2());
                                        contato.setEmail(cliEF2[0].getEmail());
                                        var obs = "REF.PES. 1:" + cliEF2[0].getNomeRef1().trim() + " TEL: " + cliEF2[0].getTelefoneRef1().trim() + "/" +
                                                "REF.PES. 2:" + cliEF2[0].getNomeRef2().trim() + " TEL: " + cliEF2[0].getTelefoneRef2().trim();
                                        if (obs.length() > 100) {
                                            contato.setObservacoes(obs.substring(0, 100));
                                        } else {
                                            contato.setObservacoes(obs);
                                        }

                                        var listaContatos = new ContatoDTO[]{contato};
                                        cli.setContatos(listaContatos);

                                        pontoErro = "gravando cliente no Winthor";
                                        //log.info("[clientesNuubesWinthor] -> body POST: " + cli.toString());
                                        var result = clienteWinthor.postCliente(cli);

                                        if (result != null) {
                                            if (result.getId() != null) {
                                                log.info("[clientesNuubesWinthor] - Cadastrado cliente código " + result.getId() + " para o cpf/cnpj " + Ferramentas.formatarCpfCnpj(result.getCpfCnpj(), tipoFJ));
                                            } else {
                                                log.warn("[clientesNuubesWinthor] - Não foi possível cadastrar o cliente CPF/CNPJ " + Ferramentas.formatarCpfCnpj(cli.getCpfCnpj(), tipoFJ) +
                                                        "\n  -> Msg de erro: " + result.getError()
                                                );
                                            }
                                        }
                                    } catch (CepException e) {
                                        log.warn("[clientesNuubesWinthor] - Não foi possível buscar dados referentes ao CEP: " + cliEF[0].getCep() + "... Verifique se o CEP está correto!");
                                    } catch (Exception e) {
                                        log.warn("[clientesNuubesWinthor](pontoErro: " + pontoErro + "): Erro -> " + e);
                                    }
                                } else {
                                    if (cli.getId() != null) {
                                        log.info("[clientesNuubesWinthor] - Cliente já cadastrado com código " + cli.getId());
                                    }
                                    if (cli.getError() != null) {
                                        log.info("[clientesNuubesWinthor] - Erro ao buscar cadastro de cliente no Winthor: " + cli.getError());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            log.info("[clientesNuubesWinthor] - Fim da verificação.");
        } catch (Exception e) {
            log.warn("[clientesNuubesWinthor]: Erro[" + pontoErro + "] -> " + e);
        }
    }

    //@Async(value="taskWinthorNuubes")
    @Scheduled(fixedDelay = 600000, initialDelay = 300000)
    public void clientesWinthorNuubes() {
        try {
            var dtHoje = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dtHoje);
            cal.add(Calendar.DATE, -3);
            var dtNova = cal.getTime();
            var dataBr = Ferramentas.dataFormatada(dtNova, "dd/MM/yyyy");

            log.info("[clientesWinthorNuubes] - Sincronizando cadastros de clientes \"Winthor -> Nuubes\", a partir de " + dataBr);

            var clientes = clienteWinthor.getClientesPorDiasCadastro(3);
            if (clientes != null) {
                if (clientes.length > 0) {
                    log.info("[clientesWinthorNuubes] - Encontrados " + clientes.length + " cadastros a serem verificados...");
                    for (int i = 0; i < clientes.length; i++) {
                        log.info("[clientesNuubesWinthor]-------------------------------------------------------------------");
                        log.info("[clientesWinthorNuubes] - Verificando cpf/cnpj: " + clientes[i].getCpfCnpj());
                        String tipo;
                        if (Ferramentas.somenteNumeros(clientes[i].getCpfCnpj()).length() == 11) {
                            tipo = "F";
                        } else {
                            tipo = "J";
                        }
                        //var cliNuubes = clienteNuubes.getClientePorCnpj(clientes[i].getCpfCnpj(), tipo);
                        if (!clienteNuubes.existeCliente(clientes[i].getCpfCnpj(), tipo)) {
                            log.info("[clientesWinthorNuubes] - Inserindo novo cliente: " + Ferramentas.removerAcentos(clientes[i].getNome()));
                            var custDTO = new CustomerDTO();
                            try {
                                custDTO.setExternalCode(clientes[i].getId());
                                custDTO.setName(clientes[i].getNome());
                                custDTO.setFantasyName(clientes[i].getNome());

                                if (tipo.equalsIgnoreCase("F")) {
                                    custDTO.setCpf(clientes[i].getCpfCnpj());
                                } else {
                                    custDTO.setCnpj(clientes[i].getCpfCnpj());
                                }

                                if (clientes[i].getEnderecos() != null) {
                                    log.info("[clientesWinthorNuubes] - Verificando endereços...");
                                    var enderecos = clientes[i].getEnderecos();
                                    for (int e = 0; e < enderecos.length; e++) {
                                        if (enderecos[e].getTipoEndereco() == TipoEndereco.Comercial) {
                                            log.info("[clientesWinthorNuubes] - Setando endereço: \n" +
                                                    "...Cidade: " + enderecos[e].getCidade() + "\n" +
                                                    "...Uf: " + enderecos[e].getUf() + "\n" +
                                                    "...Rua: " + enderecos[e].getRua() + "\n" +
                                                    "...Nr.: " + enderecos[e].getNumero() + "\n" +
                                                    "...Bairro: " + enderecos[e].getBairro() + "\n");
                                            custDTO.setCity(enderecos[e].getCidade());
                                            custDTO.setStreet(enderecos[e].getRua());
                                            custDTO.setNumber(enderecos[e].getNumero());
                                            custDTO.setDistrict(enderecos[e].getBairro());
                                            custDTO.setUf(enderecos[e].getUf());
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                log.warn("[clientesWinthorNuubes] - Erro ao traduzir dados do cadastro... Cliente: " + Ferramentas.removerAcentos(clientes[i].getNome()));
                            }
                            clienteNuubes.postCliente(custDTO);
                        } else {
                            log.info("[clientesWinthorNuubes] - Cliente já existe: " + clientes[i].getNome());
                        }
                    }
                } else {
                    log.info("[clientesWinthorNuubes] - Não foram encontrados novos cadastros a serem verificados.");
                }
            } else {
                log.info("[clientesWinthorNuubes] - Não foram encontrados novos clientes a serem verificados.");
            }
            log.info("[clientesWinthorNuubes] - Fim da atualização \"Winthor -> Nuubes\"");
        } catch (Exception e) {
            log.error("[clientesWinthorNuubes] - Erro durante atualização: " + e);
        }
    }


    private ClienteDTO dadosComerciais(ClienteDTO dados) {
        ClienteDTO result = dados;
        var planoPagto = new PlanoPagtoDTO();
        var cobrancaDto = new CobrancaDTO();
        planoPagto.setId("21");
        cobrancaDto.setId("D");
        result.setPlanoPagamento(planoPagto);
        result.setCobranca(cobrancaDto);
        result.setAtivo(true);

        try {
            for (var x = 0; x < result.getEnderecos().length; x++) {
                if (result.getEnderecos()[x].getTipoEndereco() == TipoEndereco.Comercial) {

                    var rca1 = new RcaDTO();
                    var rca2 = new RcaDTO();
                    rca1.setOrdem(1L);
                    rca2.setOrdem(2L);

                    switch (result.getEnderecos()[x].getUf().toUpperCase()) {
                        case "BA":
                        case "DF":
                            rca1.setId("77");
                            rca2.setId("63");
                            break;
                        default:
                            rca1.setId("2");
                            rca2.setId("63");
                            break;
                    }

                    var rcas = new RcaDTO[]{rca1, rca2};
                    result.setVendedores(rcas);

                    if (result.getTipoPessoa().equalsIgnoreCase("Física")) {
                        result.setConsumidorFinal(true);
                        result.setContribuinte(false);
                    } else {
                        result.setConsumidorFinal(false);
                        result.setContribuinte(true);
                    }

                    if (result.getInscricaoEstadual().equalsIgnoreCase("ISENTO")) {
                        result.setConsumidorFinal(true);
                        result.setContribuinte(false);
                    } 
                    
                    result.setConsumidorFinal(result.getInscricaoEstadual().equalsIgnoreCase("ISENTO"));
                    result.setContribuinte(!result.getInscricaoEstadual().equalsIgnoreCase("ISENTO"));

                    result.setAcessarPlanoPagamentosNegociados(true);
                }
            }
        } catch (Exception e) {
            log.warn("[dadosComerciais] - Erro: " + e);
        }
        return result;
    }

    private ClienteDTO dadosFiscais(ClienteDTO dados) {
        ClienteDTO result = dados;
        try {
            for (var x = 0; x < result.getEnderecos().length; x++) {
                if (result.getEnderecos()[x].getTipoEndereco() == TipoEndereco.Comercial) {
                    result.setFilial(null);
                    var filial = new FilialDTO();
                    switch (result.getEnderecos()[x].getUf().toUpperCase()) {
                        case "TO":
                            result.setIsentoIcms(true);
                            result.setIsentoIpi(true);
                            result.setCalculaSt(false);
                            filial.setId("3");
                            break;
                        case "GO":
                            result.setIsentoIcms(false);
                            result.setIsentoIpi(false);
                            result.setCalculaSt(true);
                            filial.setId("1");
                            break;
                        case "BA":
                            result.setIsentoIcms(false);
                            result.setIsentoIpi(true);
                            result.setCalculaSt(true);
                            filial.setId("6");
                            break;
                        case "MT":
                            if (Ferramentas.verificarTipoFJ(result.getCpfCnpj()).equalsIgnoreCase("F")) {
                                result.setIsentoIcms(true);
                                result.setIsentoIpi(true);
                                result.setCalculaSt(false);
                            } else {
                                result.setIsentoIcms(false);
                                result.setIsentoIpi(false);
                                result.setCalculaSt(true);
                            }
                            filial.setId("1");
                            break;
                        case "MG":
                            result.setIsentoIcms(false);
                            result.setIsentoIpi(true);
                            result.setCalculaSt(true);
                            filial.setId("5");
                            break;
                        case "DF":
                            result.setIsentoIcms(true);
                            result.setIsentoIpi(true);
                            result.setCalculaSt(false);
                            filial.setId("2");
                            break;
                        case "PA":
                            result.setIsentoIcms(false);
                            result.setIsentoIpi(true);
                            result.setCalculaSt(false);
                            filial.setId("7");
                            break;
                        default:
                            result.setIsentoIcms(false);
                            result.setIsentoIpi(true);
                            result.setCalculaSt(false);
                            filial.setId("8");
                            break;
                    }

                    if (filial.getId() != null) {
                        result.setFilial(filial);
                    }
                }
            }
        } catch (Exception e) {
            log.warn("[dadosFiscais] - Erro: " + e);
        }
        return result;
    }
}


