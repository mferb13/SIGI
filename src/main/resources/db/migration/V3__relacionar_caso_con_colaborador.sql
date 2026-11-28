ALTER TABLE caso
ADD COLUMN id_colaborador INTEGER NOT NULL;

ALTER TABLE caso
ADD CONSTRAINT fk_caso_colaborador
FOREIGN KEY (id_colaborador)
REFERENCES colaborador(id_colaborador);

CREATE INDEX idx_caso_id_colaborador
ON caso(id_colaborador);
