import { Navigate } from "react-router-dom";
import useAuth from "../Hooks/useAuth";
import { SideBar } from "../Componentes/sideBar";

interface Props {
    page: any
}


export const PrivateRoute = (props: Props) => {
    const auth = useAuth()

    if (auth?.token) {
        return <>
            <SideBar />
            {props.page}
            </>
    }

    return <Navigate to={"/"} replace />
}