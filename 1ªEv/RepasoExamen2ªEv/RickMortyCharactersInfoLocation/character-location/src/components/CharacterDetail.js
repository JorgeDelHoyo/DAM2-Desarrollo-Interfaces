import { useEffect, useState } from "react";
import CharacterCard from "./CharacterCard";
import CharacterList from "./CharacterList";

function CharacterDetail({locationUrl, onClose}) {
    const [locationInfo, setLocationInfo] = useState(null);
    const [residents, setResidents] = useState([]);
    const [loading, setLoading] = useState(false);

    const fetchLocation = async () => {
        setLoading(true);
        try {
            const response = await fetch(locationUrl);  
            const data = await response.json();

            if(response.ok){
                setLocationInfo(data || [])

                // Array temporal vacio
                const chars = []

                /** 
                 *  Bucle recorriendo data.residents
                 * Por cada 'url' que haya dentro de 'data.residents' 
                 **/
                for (const url of data.residents){
                    const responseResidents = await fetch(url)
                    const dataResidents = await responseResidents.json();
                    chars.push(dataResidents)
                }

                // Guardar estado
                setResidents(chars);
            }else{
                setLocationInfo([])
            }
        } catch (err) {
            setLocationInfo([])
        }finally{
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchLocation();
    }, [locationUrl]);


    if(!locationInfo) return <div>Cargando información del lugar...</div>
    return(
        <div>
            <div>
                <button onClick={onClose}>X</button>
                {/**Info del lugar */}
                <h2>{locationInfo.name}</h2>
                <p><strong>Tipo:</strong> {locationInfo.type}</p>
                <p><strong>Dimensión:</strong> {locationInfo.dimension}</p>
                {/**Info de residentes */}
                <h3>Residentes:</h3>
                <div className="residents-grid">
                    <CharacterList
                    characters={residents}
                    onCharacterClick={() => {}}
                    />
                </div>
            </div>
        </div>
    );
}

export default CharacterDetail