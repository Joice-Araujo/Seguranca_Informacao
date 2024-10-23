import React, { useState } from 'react'
import { Blog } from '../Interfaces/Blog'


export default function PostCard(props : Blog ) {
  const [dropBox, setDropbox] = useState<boolean>(false)

  return (
    <>
      <div className='relative p-5 flex flex-col  justify-between w-2/4 bg-white rounded-md h-max mx-auto mt-6 border-solid'>
        <div className='flex flex-row justify-between items-center '>
          <div><h1 className='text-4xl'>{props.titulo}</h1></div>
          {/* <div className='flex flex-col space-y-3'>
            <button className='btn p-1 rounded-md bg-orange-300'>Editar</button>
            <button className='btn p-1 rounded-md bg-orange-300'>Apagar</button>
          </div> */}
        </div>
        {!dropBox && (
          <p onClick={() => setDropbox(!dropBox)}>Ler mais</p>
        )}

        {dropBox && (
          <div>
            <p onClick={() => setDropbox(!dropBox)}>Ler menos</p>
            <p className='bg-white'>{props.conteudo}</p>
          </div>
        )}


      </div>
    </>
  )
}
