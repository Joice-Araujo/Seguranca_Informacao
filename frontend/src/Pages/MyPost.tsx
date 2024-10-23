import React, { useEffect, useState } from 'react'
import { blogService } from '../Services/blog-service'
import PostCard from '../Componentes/PostCard';
import { Blog } from '../Interfaces/Blog';


export default function MyPosts() {
    const [posts, setPosts] = useState<Blog[]>([])

    useEffect(() => {
        blogService.getBlogByUser().then(resp => {
            console.log(resp.data)
            setPosts(resp.data)
        })
    }, [])
    return (
        <div className='w-full'>
            {posts.length > 0 ? (
                <div className='flex flex-col h-screen overflow-y-scroll scrollbar-thin scrollbar-thumb-gray-500 scrollbar-track-gray-200'>
                    {posts.map(post => {
                        return <PostCard id={post.id} userId={post.userId} createdAt={post.createdAt} titulo={post.titulo} conteudo={post.conteudo}></PostCard>
                    })}
                </div>
            ) : <p className='text-5xl'>Não há postagens em seu perfil</p>}
        </div>
    )
}
