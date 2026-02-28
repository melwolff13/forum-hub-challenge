create table respostas (
    id bigint not null auto_increment,
    mensagem text not null,
    data_criacao datetime not null,

    autor_id bigint not null,
    topico_id bigint not null,

    primary key (id),

    foreign key (autor_id) references usuarios(id),
    foreign key (topico_id) references topicos(id)
)