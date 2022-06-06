package br.com.i7solution.integradornuubes.mock;

import java.util.Date;

import br.com.i7solution.integradornuubes.entities.dtos.ClienteDTO;
import br.com.i7solution.integradornuubes.entities.dtos.ContatoDTO;
import br.com.i7solution.integradornuubes.entities.dtos.EnderecoDTO;
import br.com.i7solution.integradornuubes.entities.dtos.FilialDTO;
import br.com.i7solution.integradornuubes.entities.dtos.PlanoPagtoDTO;
import br.com.i7solution.integradornuubes.entities.dtos.PracaDTO;
import br.com.i7solution.integradornuubes.entities.dtos.RcaDTO;
import br.com.i7solution.integradornuubes.tipos.TipoContato;
import br.com.i7solution.integradornuubes.tipos.TipoEndereco;

public class ClienteDtoMock {
	
	private ClienteDTO cliente = null;
	
	
	public ClienteDtoMock() {
		cliente = new ClienteDTO();
		cliente.setId(null);
		cliente.setNome("ROBSON FERREIRA CAMARA");
		cliente.setNomeFantasia("PANIFICADORA SKINA DO PAO");
		cliente.setCpfCnpj("02184731179");
		cliente.setCpfCnpjEntrega("02184731179");
		cliente.setDataNascimento(null);
		cliente.setTipoPessoa("Física");
		cliente.setInscricaoEstadual("isento");
		cliente.setInscricaoMunicipal(null);
		cliente.setEmail("rf1002572@gmail.com");
		cliente.setTelefoneCelular("062991332309");
		cliente.setTelefoneFixo("062982709550");
		
		PlanoPagtoDTO planoPagamento = new PlanoPagtoDTO();
		planoPagamento.setId("21");
		planoPagamento.setDescricao(null);
		cliente.setPlanoPagamento(planoPagamento);
		
		cliente.setAcessarPlanoPagamentosNegociados(true);
		
		RcaDTO[] vendedores = new RcaDTO[2];
		RcaDTO vendedor1 = new RcaDTO();
		vendedor1.setId("2");
		vendedor1.setNome(null);
		vendedor1.setOrdem(1L);
		RcaDTO vendedor2 = new RcaDTO();
		vendedor2.setId("63");
		vendedor2.setNome(null);
		vendedor2.setOrdem(2L);
		vendedores[0] = vendedor1;
		vendedores[1] = vendedor2;
		cliente.setVendedores(vendedores);
		
		PracaDTO praca = new PracaDTO();
		praca.setId("250");
		praca.setDescricao("APARECIDA DE GOIANIA - PF");
		cliente.setPraca(praca);;
		
		PracaDTO pracaCobranca = new PracaDTO();
		pracaCobranca.setId("250");
		pracaCobranca.setDescricao("APARECIDA DE GOIANIA - PF");
		cliente.setPracaCobranca(pracaCobranca);
		
		
		EnderecoDTO[] enderecos = new EnderecoDTO[3];
		EnderecoDTO endereco1 = new EnderecoDTO();
		endereco1.setId(null);
		endereco1.setIdCliente(null);
		endereco1.setNumero("SN");
		endereco1.setCep("74921360");
		endereco1.setBairro("SETOR TIRADENTES");
		endereco1.setComplemento("QD. 20 LT. 08");
		endereco1.setPontoRefer("QD. 20 LT. 08");
		endereco1.setUf("GO");
		endereco1.setCidade("APARECIDA DE GO");
		endereco1.setCodigoIbge(5201405L);
		endereco1.setCodigoPais(1058L);
		endereco1.setRua("RUA X-3");
		endereco1.setTipoEndereco(TipoEndereco.Comercial);
		
		EnderecoDTO endereco2 = new EnderecoDTO();
		endereco2.setId(null);
		endereco2.setIdCliente(null);
		endereco2.setNumero("SN");
		endereco2.setCep("74921360");
		endereco2.setBairro("SETOR TIRADENTES");
		endereco2.setComplemento("QD. 20 LT. 08");
		endereco2.setPontoRefer("QD. 20 LT. 08");
		endereco2.setUf("GO");
		endereco2.setCidade("APARECIDA DE GO");
		endereco2.setCodigoIbge(5201405L);
		endereco2.setCodigoPais(1058L);
		endereco2.setRua("RUA X-3");
		endereco2.setTipoEndereco(TipoEndereco.Entrega);
		
		EnderecoDTO endereco3 = new EnderecoDTO();
		endereco3.setId(null);
		endereco3.setIdCliente(null);
		endereco3.setNumero("SN");
		endereco3.setCep("74921360");
		endereco3.setBairro("SETOR TIRADENTES");
		endereco3.setComplemento("QD. 20 LT. 08");
		endereco3.setPontoRefer("QD. 20 LT. 08");
		endereco3.setUf("GO");
		endereco3.setCidade("APARECIDA DE GO");
		endereco3.setCodigoIbge(5201405L);
		endereco3.setCodigoPais(1058L);
		endereco3.setRua("RUA X-3");
		endereco3.setTipoEndereco(TipoEndereco.Cobranca);

		
		enderecos[0] = endereco1;
		enderecos[1] = endereco3;
		enderecos[2] = endereco3;
		
		cliente.setEnderecos(enderecos);
		
		ContatoDTO[] contatos = new ContatoDTO[1];
		ContatoDTO contato = new ContatoDTO();
		contato.setId(null);
		contato.setNome("Robson Ferreira Camara");
		contato.setIdCliente(null);
		contato.setCpfCnpj("021.847.311-70");
		contato.setTipoContato(TipoContato.Socio);
		contato.setRg("5122509/SSP/GO");
		contato.setDataNascimento(new Date());
		contato.setEndereco("Viela 1° de Abril,SN,Qd. 8-A Lt. 14");
		contato.setBairro("Setor Tiradentes");
		contato.setCidade("Aparecida de Goiânia");
		contato.setEstado("GO");
		contato.setCep("74922696");
		contato.setTelefone("062 98270-9550");
		contato.setCelular("06299133-2309");
		contato.setEmail("rf1002572@gmail.com");
		contato.setObservacoes("REF.PES. 1:Indianara  - esposa TEL: 06299133-2309/REF.PES. 2:Luci - Tia TEL: 06299187-2667");
		contatos[0] = contato;
		cliente.setContatos(contatos);
		
				
		
		cliente.setIsentoIcms(false);
		cliente.setIsentoIpi(false);
		cliente.setCalculaSt(true);
		cliente.setContribuinte(false);
		cliente.setConsumidorFinal(true);
		cliente.setEmailNfe("rf1002572@gmail.com");
		cliente.setEmailCobranca("rf1002572@gmail.com");
		cliente.setEmailRecebedor("rf1002572@gmail.com");
		cliente.setTelefoneCobranca("062982709550");
		cliente.setTelefoneEntrega("062982709550");
		cliente.setTelefoneComercial("062982709550");
		
		FilialDTO filial = new FilialDTO();
		filial.setId("1");
		filial.setCnpj(null);
		filial.setRazaoSocial(null);
		filial.setNomeFantasia(null);
		cliente.setFilial(filial);
		
		
		
	}
	
	public ClienteDTO getCliente() {
		return this.cliente;
	}

}
