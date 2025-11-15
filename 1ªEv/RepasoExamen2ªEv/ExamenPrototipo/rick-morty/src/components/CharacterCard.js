import React from 'react';
import './CharacterCard.css';

function CharaterCard({character, onCardClick}){
    return(
        <div className='character-card' onClick={() => onCardClick(character)}>
            <img src={character.image} />
            <h3>{character.name}</h3>
        </div>
    );
}

export default CharaterCard;