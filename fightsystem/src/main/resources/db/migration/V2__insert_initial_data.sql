INSERT INTO modalities(name) VALUES
('Musculação'),
('Funcional'),
('Jiu-Jitsu'),
('Muay Thai'),
('Pilates');

INSERT INTO subscription (modalitie_id, name, monthly_value)
SELECT id, 'Mensal', 120.00 FROM modalities WHERE name = 'Musculação';

INSERT INTO subscription (modalitie_id, name, monthly_value)
SELECT id, 'Trimestral', 330.00 FROM modalities WHERE name = 'Musculação';

INSERT INTO subscription (modalitie_id, name, monthly_value)
SELECT id, 'Mensal', 150.00 FROM modalities WHERE name = 'Funcional';

INSERT INTO subscription (modalitie_id, name, monthly_value)
SELECT id, 'Mensal', 180.00 FROM modalities WHERE name = 'Jiu-Jitsu';

INSERT INTO graduation (modalitie_id, name)
SELECT id, 'Faixa Branca' FROM modalities WHERE name = 'Jiu-Jitsu';

INSERT INTO graduation (modalitie_id, name)
SELECT id, 'Faixa Azul' FROM modalities WHERE name = 'Jiu-Jitsu';

INSERT INTO graduation (modalitie_id, name)
SELECT id, 'Faixa Roxa' FROM modalities WHERE name = 'Jiu-Jitsu';