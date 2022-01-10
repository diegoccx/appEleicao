ELEIÇAO
id_eleicao NOT NULL
nome NOT NULL
data_inicio NOT NULL
data_fim NOT NULL

CREATE TABLE IF NOT EXISTS `eleicao` (
  `id_eleicao` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(25) NOT NULL,
  data_inicio DATETIME NOT NULL,
  data_fim DATETIME NOT NULL,
  PRIMARY KEY (`id_eleicao`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

select * from candidato
select * from usuario

SELECT * FROM cargo_eleicao 

SELECT * FROM candidato_voto



CARGO_ELEICAO
id_cargo NOT NULL
nome NOT NULL
id_eleicao NOT NULL

DROP TABLE IF EXISTS `cargo_eleicao`;

CREATE TABLE IF NOT EXISTS `cargo_eleicao` (
  `id_cargo_eleicao` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `id_eleicao` int(11) NOT NULL,
  PRIMARY KEY (`id_cargo_eleicao`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
COMMIT;

ALTER TABLE `cargo_eleicao` ADD CONSTRAINT `fk_eleicao` 
FOREIGN KEY ( `id_eleicao` ) REFERENCES `eleicao` ( `id_eleicao` ) ;


CANDIDATO
id_candidato_cargo
id_cargo_eleicao
nome
foto blob
quantidade_votos

CREATE TABLE IF NOT EXISTS `candidato` (
  `id_candidato_cargo` int(11) NOT NULL AUTO_INCREMENT,
  `id_cargo_eleicao` int(11) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `foto` blob ,
  `quantidade_votos` int(11) NOT NULL,
  PRIMARY KEY (`id_candidato_cargo`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
COMMIT;
ALTER TABLE `candidato` ADD CONSTRAINT `fk_candidato_cargo` 
FOREIGN KEY ( `id_cargo_eleicao` ) REFERENCES `cargo_eleicao` ( `id_cargo_eleicao` ) ;

CANDIDATO_VOTO
id_candidado_cargo
id_usuario
cpf
nome
protocolo


CREATE TABLE IF NOT EXISTS `candidato_voto` (
  `id_candidato_voto` int(11) NOT NULL AUTO_INCREMENT,
  `id_candidato_cargo` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cpf` varchar(20) NOT NULL,
  `protocolo` varchar(20) NOT NULL,
  PRIMARY KEY (`id_candidato_voto`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
COMMIT;
ALTER TABLE `candidato_voto` ADD CONSTRAINT `fk_candidato_voto_cargo` 
FOREIGN KEY ( `id_candidato_cargo` ) REFERENCES `candidato` ( `id_candidato_cargo` ) ;

--
-- Extraindo dados da tabela `telefone_tipo`
--

INSERT INTO `telefone_tipo` (`id_telefone_tipo`, `nome`) VALUES
(1, 'Residencial'),
(2, 'Celular'),
(3, 'Comercial'),
(4, 'Fax'),
(5, 'NÃ£o Definido'),
(6, 'Tipo 0800');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
   `email` varchar(255) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------



