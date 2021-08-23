    create table forma_pagamento (
       id bigint not null auto_increment,
        descricao varchar(80) not null,
        primary key (id)
    ) engine = InnoDB;

    create table grupo (
       id bigint not null auto_increment,
        nome varchar(60),
        primary key (id)
    ) engine = InnoDB;

    create table permissao (
       id bigint not null auto_increment,
        descricao varchar(80) not null,
        nome varchar(60) not null,
        grupo_id bigint,
        primary key (id)
    ) engine = InnoDB;

    create table produto (
       id bigint not null auto_increment,
        ativo bit not null,
        descricao varchar(80),
        nome varchar(60),
        preco decimal(19,2),
        restaurante_id bigint,
        primary key (id)
    ) engine = InnoDB;

    create table restaurante (
       id bigint not null auto_increment,
        data_atualizacao datetime not null,
        data_cadastro datetime not null,
        endereco_bairro varchar(100),
        endereco_cep varchar(100),
        endereco_complemento varchar(100),
        endereco_logradouro varchar(100),
        endereco_numero varchar(20),
        nome varchar(60) not null,
        taxa_frete decimal(19,2) not null,
        cozinha_id bigint not null,
        endereco_cidade_id bigint,
        primary key (id)
    ) engine = InnoDB;

    create table restaurante_forma_pagamento (
       restaurante_id bigint not null,
        forma_pagamento_id bigint not null
    ) engine = InnoDB;

    create table restaurante_produtos (
       restaurante_id bigint not null,
        produtos_id bigint not null
    ) engine = InnoDB;

    create table usuario (
       id bigint not null auto_increment,
        data_cadastro datetime not null,
        email varchar(60),
        nome varchar(60),
        senha varchar(10),
        primary key (id)
    ) engine = InnoDB;

    create table usuario_grupo (
       usuario_id bigint not null,
        grupo_id bigint not null
    ) engine = InnoDB;


    alter table permissao 
       add constraint FK_grupo 
       foreign key (grupo_id) 
       references grupo (id);

    alter table produto 
       add constraint FK_restaurante
       foreign key (restaurante_id) 
       references restaurante (id);

    alter table restaurante 
       add constraint FK_cozinha 
       foreign key (cozinha_id) 
       references cozinha (id);

    alter table restaurante 
       add constraint FK_endereco 
       foreign key (endereco_cidade_id) 
       references cidade (id);

    alter table restaurante_forma_pagamento 
       add constraint FK_forma_pagamento
       foreign key (forma_pagamento_id) 
       references forma_pagamento (id);

    alter table restaurante_forma_pagamento 
       add constraint FK_restaurante_pgto
       foreign key (restaurante_id) 
       references restaurante (id);

    alter table restaurante_produtos 
       add constraint FK_produto_restaurante 
       foreign key (produtos_id) 
       references produto (id);

    alter table restaurante_produtos 
       add constraint FK_restaurante_produto
       foreign key (restaurante_id) 
       references restaurante (id);

    alter table usuario_grupo 
       add constraint FK_grupo_usuario
       foreign key (grupo_id) 
       references grupo (id);

