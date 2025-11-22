import React from 'react';
import './CharacterCard.css';

function CharacterCard({ character, onClick }) {
    return (
        <div className="character-card" onClick={onClick}>
            <img src={character.image} />
            <h3 className="character-name">{character.name}</h3>
        </div>
    );
}

export default CharacterCard;