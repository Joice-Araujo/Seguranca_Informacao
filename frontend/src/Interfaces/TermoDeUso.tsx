export type OpcaoTermosDeUsoDto = {
  descricao: string;
  aceito: boolean;
};

export type TermosDeUsoDto = {
  versao: string;
  descricao: string;
  opcoes: OpcaoTermosDeUsoDto[];
};

export type AssinarTermo = {
  idUsuario: string;
  opcoes: OpcaoTermosDeUsoDto[];
};
