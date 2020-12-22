package com.joaodanieljr.projetoLojaJava;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joaodanieljr.projetoLojaJava.domain.Categoria;
import com.joaodanieljr.projetoLojaJava.domain.Cidade;
import com.joaodanieljr.projetoLojaJava.domain.Cliente;
import com.joaodanieljr.projetoLojaJava.domain.Endereco;
import com.joaodanieljr.projetoLojaJava.domain.Estado;
import com.joaodanieljr.projetoLojaJava.domain.ItemPedido;
import com.joaodanieljr.projetoLojaJava.domain.Pagamento;
import com.joaodanieljr.projetoLojaJava.domain.PagamentoComBoleto;
import com.joaodanieljr.projetoLojaJava.domain.PagamentoComCartao;
import com.joaodanieljr.projetoLojaJava.domain.Pedido;
import com.joaodanieljr.projetoLojaJava.domain.Produto;
import com.joaodanieljr.projetoLojaJava.domain.enums.EstadoPagamento;
import com.joaodanieljr.projetoLojaJava.domain.enums.TipoCliente;
import com.joaodanieljr.projetoLojaJava.repositories.CategoriaRepository;
import com.joaodanieljr.projetoLojaJava.repositories.CidadeRepository;
import com.joaodanieljr.projetoLojaJava.repositories.ClienteRepository;
import com.joaodanieljr.projetoLojaJava.repositories.EnderecoRepository;
import com.joaodanieljr.projetoLojaJava.repositories.EstadoRepository;
import com.joaodanieljr.projetoLojaJava.repositories.ItemPedidoRepository;
import com.joaodanieljr.projetoLojaJava.repositories.PagamentoRepository;
import com.joaodanieljr.projetoLojaJava.repositories.PedidoRepository;
import com.joaodanieljr.projetoLojaJava.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoLojaJavaApplication implements CommandLineRunner  { 
	// command serve para rodar algo antes da app inicializar.
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoLojaJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sao Paulo", est2);
		Cidade c3 = new Cidade(null, "Osasco", est2);		

		Cliente cl1 = new Cliente(null, "Ana Paula", "ana@gmail.com", "345.545.484-82", TipoCliente.PESSOAFISICA );
		cl1.getTelefones().addAll(Arrays.asList("3455-8899", "94567-8965"));
		
		Cliente cl2 = new Cliente(null, "Joao Daniel", "joaodanieljr@gmail.com", "123.456.789-101", TipoCliente.PESSOAFISICA );
		cl2.getTelefones().addAll(Arrays.asList("3455-8877", "98067-8965"));
		
		Endereco e1 = new Endereco(null, "Rua Edmundo", "5566", "ap 55", "jd elisa", "06534-450", cl1, c1);
		Endereco e2 = new Endereco(null, "Rua Munhoz", "55", "ap 38", "jd Piratininga", "06534-585", cl2, c3);
		Endereco e3 = new Endereco(null, "Rua do ze", "58", "ap 547", "Vila mariana", "04534-578", cl2, c2);
		
		cl1.getEnderecos().addAll(Arrays.asList(e1));
		cl2.getEnderecos().addAll(Arrays.asList(e2, e3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		clienteRepository.saveAll(Arrays.asList(cl1, cl2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido pd1 = new Pedido(null, sdf.parse("30/09/2020 20:15"), cl1, e1);
		Pedido pd2 = new Pedido(null, sdf.parse("18/12/2020 00:11"), cl1, e2);
		Pedido pd3 = new Pedido(null, sdf.parse("10/12/2020 00:56"), cl2, e3);
		
		Pagamento pg1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pd1, 3);
		pd1.setPagamento(pg1);
		
		Pagamento pg2 = new PagamentoComCartao(null, EstadoPagamento.CANCELADO, pd2, 6);
		pd2.setPagamento(pg2);
		
		Pagamento pg3 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pd3, sdf.parse("10/12/2020 00:56"), null);
		pd3.setPagamento(pg3);
		
		cl1.getPedidos().addAll(Arrays.asList(pd1, pd2));
		cl2.getPedidos().addAll(Arrays.asList(pd3));
		
		pedidoRepository.saveAll(Arrays.asList(pd1, pd2, pd3));
		pagamentoRepository.saveAll(Arrays.asList(pg1, pg2, pg3));
		
		
		ItemPedido it1 = new ItemPedido(pd1, p1, 0.00, 1, 2000.00);
		ItemPedido it2 = new ItemPedido(pd1, p3, 0.00, 2, 80.00);
		ItemPedido it3 = new ItemPedido(pd2, p2, 90.00, 1, 800.00);
		
		pd1.getItens().addAll(Arrays.asList(it1, it2));
		pd2.getItens().addAll(Arrays.asList(it3));
		
		p1.getItens().addAll(Arrays.asList(it1));
		p2.getItens().addAll(Arrays.asList(it3));
		p3.getItens().addAll(Arrays.asList(it2));
		
		itemPedidoRepository.saveAll(Arrays.asList(it1, it2, it3));
		
	}

}
