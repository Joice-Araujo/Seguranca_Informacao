import React, { useEffect } from 'react'
import { blogService } from '../Services/blog-service'

export default function MyPosts() {

    useEffect(() => {
        blogService.getBlogByUser();
    })
    return (
        <>
        </>
    )
}
