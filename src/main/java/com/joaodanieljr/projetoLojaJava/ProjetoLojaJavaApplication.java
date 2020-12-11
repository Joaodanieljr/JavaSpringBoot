package com.joaodanieljr.projetoLojaJava;

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
import com.joaodanieljr.projetoLojaJava.domain.Produto;
import com.joaodanieljr.projetoLojaJava.domain.enums.TipoCliente;
import com.joaodanieljr.projetoLojaJava.repositories.CategoriaRepository;
import com.joaodanieljr.projetoLojaJava.repositories.CidadeRepository;
import com.joaodanieljr.projetoLojaJava.repositories.ClienteRepository;
import com.joaodanieljr.projetoLojaJava.repositories.EnderecoRepository;
import com.joaodanieljr.projetoLojaJava.repositories.EstadoRepository;
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

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		

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
		
		clienteRepository.saveAll(Arrays.asList(cl1, cl2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
	}

}
