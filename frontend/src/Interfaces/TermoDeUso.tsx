export type OpcaoTermosDeUsoDto = {
  descricao: string;
  aceito: boolean;
};

export type TermosDeUsoDto = {
  versao: string;
  descricao: string;
  opcoes: OpcaoTermosDeUsoDto[];
  assinado ?: boolean
};

export type AssinarTermo = {
  idUsuario: string;
  opcoes: OpcaoTermosDeUsoDto[];
};
