import { useState,useEffect} from "react";

export function useFetch(url){
    const [data, setData] = useState(null);
    const[loading, setLoading] = useState(true);
    const[error, setError] = useState(null);
    const[controller, setController] = useState(null);

    useEffect(() => {
        // Para evitar fugas, limpiamos con Abort
        const abortController = new AbortController();
        setController(abortController);

        setLoading(true);
        fetch(url,{signal: abortController.signal})
            .then((response) => response.json())
            .then((data) => setData(data))
            .catch((error) => {
                if(error.name === "AbortError"){
                    console.log("Request cancelada");
                }else{
                    setError(error);
                }
            })
            .finally(() => setLoading(false));

        // Cuando ya no está disponible en pantalla
        return () => abortController.abort();
    }, []);

    // Para hacer uso del abort
    const handleCancelRequest = () => {
        if(controller){
            controller.abort();
            setError("Request cancelada");
        }
    }

    return { data, loading, error, handleCancelRequest };
}