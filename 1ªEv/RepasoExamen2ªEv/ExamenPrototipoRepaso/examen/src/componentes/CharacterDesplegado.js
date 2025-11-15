import React from "react";
import './CharacterDesplegado.css';

function CharacterDesplegado({personajeSeleccionado, cerrarDetalles}){
    return(
        <div onClick={cerrarDetalles} className="modal-overlay">
            <div onClick={(e) => e.stopPropagation} className="modal-content">
                <button onClick={cerrarDetalles} className="modal-close">X</button>
                <h2>{personajeSeleccionado.name}</h2>
                <img src={personajeSeleccionado.image} alt={personajeSeleccionado.name} />
                <p><strong>Estado:</strong> {personajeSeleccionado.status}</p>
                <p><strong>Especie:</strong> {personajeSeleccionado.species}</p>
                <p><strong>Género:</strong> {personajeSeleccionado.gender}</p>
                <p><strong>Origen:</strong> {personajeSeleccionado.origin.name}</p>
                <p><strong>Ubicación:</strong> {personajeSeleccionado.location.name}</p>
            </div>
        </div>
    );
}

export default CharacterDesplegado;