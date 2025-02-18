import { useState } from "react";
import { Input } from "../Componentes/input";
import { OpcaoTermosDeUsoDto, TermosDeUsoDto } from "../Interfaces/TermoDeUso";
import { Btn } from "../Componentes/btn";
import { FaTrash } from "react-icons/fa";
import { termoDeUsoService } from "../Services/termodeuso-service";
import { useNavigate } from "react-router-dom";

export function TermosDeUso() {
  const [termoDeUso, setTermoDeUso] = useState<TermosDeUsoDto>({
    descricao: "",
    opcoes: [],
    versao: "",
  });

  const nav = useNavigate()

  const addOption = () => {
    const newOption: OpcaoTermosDeUsoDto = {
      aceito: false,
      descricao: "",
    };

    const listOption = [...termoDeUso.opcoes];
    listOption.push(newOption);
    setTermoDeUso({ ...termoDeUso, opcoes: listOption });
  };

  const removeOption = (indexRm: number) => {
    const listOptions = [...termoDeUso.opcoes];
    const filteredList = listOptions.filter((op, index) => index != indexRm);
    setTermoDeUso({ ...termoDeUso, opcoes: filteredList });
  };

  const handleOptionChange = (index: number, value: string) => {
    const updatedOptions = termoDeUso.opcoes.map((op, i) =>
      i === index ? { ...op, descricao: value } : op
    );
    setTermoDeUso({ ...termoDeUso, opcoes: updatedOptions });
  };

  const handleDesc = (value: string) => {
    setTermoDeUso({ ...termoDeUso, descricao: value });
  };

  const handleVersao = (value: string) => {
    setTermoDeUso({ ...termoDeUso, versao: value });
  };

  return (
    <>
      <div className="mx-auto my-auto">
        <div className="flex flex-col">
          <div className="mb-5">
            <h1 className="text-4xl mb-8">Termos de Uso</h1>
            <Input
              label="Obrigatório:"
              type="text"
              value={termoDeUso.descricao}
              setValue={(value: string) => handleDesc(value)}
              labelClassName="labelLogin text-3xl"
              inputClassName="inputLogin"
            />
            <Input
              label="Versão:"
              type="text"
              value={termoDeUso.versao}
              setValue={(value: string) => handleVersao(value)}
              labelClassName="labelLogin text-3xl mt-4"
              inputClassName="inputLogin"
            />
            <div className="mt-4">
              <h3 className="text-3xl mb-4">Opcional:</h3>
              {termoDeUso.opcoes.map((opcao, index) => (
                <div className="flex flex-row mr-3 justify-center items-center">
                  <Input
                    key={index}
                    label="Descrição:"
                    type="text"
                    value={opcao.descricao}
                    setValue={(value: string) =>
                      handleOptionChange(index, value)
                    }
                    labelClassName="labelLogin"
                    inputClassName="inputLogin"
                  />
                  <FaTrash
                    className="h-14 ml-3 mt-5 cursor-pointer"
                    onClick={() => removeOption(index)}
                  />
                </div>
              ))}
            </div>
          </div>

          <Btn
            label={"+"}
            onClick={addOption}
            btnClassName={
              "mx-auto flex items-center justify-center w-12 h-12 rounded-full bg-purple-600 text-white text-2xl hover:bg-purple-700 focus:outline-none focus:ring-2 focus:ring-purple-400 shadow-lg"
            }
          />
        </div>

        <Btn
          label={"Criar"}
          onClick={
            async () => {
                termoDeUsoService.createTermoDeUso(termoDeUso).then(resp => {
                    if (resp.status == 200) {
                        alert("Termo de uso criado com sucesso!")
                        nav("/perfil")
                    }
                })
            }
          }
          btnClassName={"mx-auto flex items-center justify-center w-32 h-12 rounded-md mt-4 bg-purple-600 text-white text-2xl hover:bg-purple-700 focus:outline-none focus:ring-2 focus:ring-purple-400 shadow-lg"}
        />
      </div>
    </>
  );
}
