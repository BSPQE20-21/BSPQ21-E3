-- CREATE TABLES --
CREATE TABLE donaciones (
	Fecha VARCHAR(10) NOT NULL, 
	Hora VARCHAR(8) NOT NULL, 
	Donativo INTEGER(3) NOT NULL, 
	Acumulado INTEGER NOT NULL,
	PRIMARY KEY(Fecha, Hora, Acumulado) 
);
