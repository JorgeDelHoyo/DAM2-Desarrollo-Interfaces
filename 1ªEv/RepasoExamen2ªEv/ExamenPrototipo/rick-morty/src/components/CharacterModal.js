import React from "react";
import './CharacterModal.css';

function CharacterModal({character, onClose}){
    return(
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={(e) => e.stopPropagation()}>
                <button className="modal-close" onClick={onClose}>X</button>

                <h2>{character.name}</h2>
                <img src={character.image} alt={character.name} />
                <p><strong>Estado:</strong> {character.status}</p>
                <p><strong>Especie:</strong> {character.species}</p>
                <p><strong>Género:</strong> {character.gender}</p>
                <p><strong>Origen:</strong> {character.origin.name}</p>
                <p><strong>Ubicación:</strong> {character.location.name}</p>
            </div>
        </div>
    );
}

export default CharacterModal;