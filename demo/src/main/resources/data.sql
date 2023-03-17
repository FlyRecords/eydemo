CREATE TABLE IF NOT EXISTS Usuarios (
  idUsuarios INT NOT NULL IDENTITY(1,1),
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  token VARCHAR(36) NOT NULL,
  isactive BIT NOT NULL,
  created DATETIME NOT NULL,
  modified DATETIME NULL,
  last_login DATETIME NULL,
  CONSTRAINT pk_Usuarios PRIMARY KEY (idUsuarios)
);

CREATE TABLE IF NOT EXISTS phones (
  idphones INT NOT NULL IDENTITY(1,1),
  number VARCHAR(7) NOT NULL,
  cytycode VARCHAR(2) NOT NULL,
  countrycode VARCHAR(2) NOT NULL,
  Usuarios_idUsuarios INT NOT NULL,
  CONSTRAINT pk_phones PRIMARY KEY (idphones, Usuarios_idUsuarios),
  CONSTRAINT fk_phones_Usuarios
    FOREIGN KEY (Usuarios_idUsuarios)
    REFERENCES Usuarios (idUsuarios)
);