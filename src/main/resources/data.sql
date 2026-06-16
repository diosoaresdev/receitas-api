INSERT INTO categorias (id, nome, descricao) VALUES
    (gen_random_uuid(), 'Italiana', 'Massas, risotos e pratos da culinária italiana')
    ON CONFLICT (nome) DO NOTHING;

INSERT INTO categorias (id, nome, descricao) VALUES
    (gen_random_uuid(), 'Brasileira', 'Pratos típicos da culinária brasileira')
    ON CONFLICT (nome) DO NOTHING;

INSERT INTO categorias (id, nome, descricao) VALUES
    (gen_random_uuid(), 'Japonesa', 'Sushis, temakis e pratos da culinária japonesa')
    ON CONFLICT (nome) DO NOTHING;

INSERT INTO ingredientes (id, nome, unidade_medida, calorias) VALUES
    (gen_random_uuid(), 'Farinha de trigo', 'g', 364.0)
    ON CONFLICT (nome) DO NOTHING;

INSERT INTO ingredientes (id, nome, unidade_medida, calorias) VALUES
    (gen_random_uuid(), 'Ovo', 'unidade', 155.0)
    ON CONFLICT (nome) DO NOTHING;

INSERT INTO ingredientes (id, nome, unidade_medida, calorias) VALUES
    (gen_random_uuid(), 'Azeite', 'ml', 884.0)
    ON CONFLICT (nome) DO NOTHING;

INSERT INTO ingredientes (id, nome, unidade_medida, calorias) VALUES
    (gen_random_uuid(), 'Tomate', 'g', 18.0)
    ON CONFLICT (nome) DO NOTHING;

INSERT INTO ingredientes (id, nome, unidade_medida, calorias) VALUES
    (gen_random_uuid(), 'Arroz', 'g', 358.0)
    ON CONFLICT (nome) DO NOTHING;