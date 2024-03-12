package com.nelioalves.cursomcp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomcp.domain.Categoria;
import com.nelioalves.cursomcp.domain.Cidade;
import com.nelioalves.cursomcp.domain.Cliente;
import com.nelioalves.cursomcp.domain.Endereco;
import com.nelioalves.cursomcp.domain.Estado;
import com.nelioalves.cursomcp.domain.Produto;
import com.nelioalves.cursomcp.domain.enuns.TipoCliente;
import com.nelioalves.cursomcp.repositories.CategoriaRepository;
import com.nelioalves.cursomcp.repositories.CidadeRepository;
import com.nelioalves.cursomcp.repositories.ClienteRepository;
import com.nelioalves.cursomcp.repositories.EnderecoRepository;
import com.nelioalves.cursomcp.repositories.EstadoRepository;
import com.nelioalves.cursomcp.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36365689722", TipoCliente.PESSOAFISICA);
		Endereco e1 = new Endereco(null, "Rua das Flores", "22", "Casa", "Jardim", "91270650", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "31822502", c2, cli1);

		cli1.getTelefones().addAll(Arrays.asList("33876733", "9156245252"));
		cli1.getEndereco().addAll(Arrays.asList(e1, e2));

		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

	}

}
