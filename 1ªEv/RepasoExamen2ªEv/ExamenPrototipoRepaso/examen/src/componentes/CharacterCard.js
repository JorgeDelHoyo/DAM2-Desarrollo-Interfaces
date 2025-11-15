import React from "react";
import './CharacterCard.css';

function CharacterCard({personaje, mostrarDetalles}){
    return(
        <div onClick={() => mostrarDetalles(personaje)} className="character-card">
            <img src={personaje.image} />
            <h3>{personaje.name}</h3>
        </div>
    );
}

export default CharacterCard;