import './CharacterCard.css';

function CharacterCard({character, onClick}) {
    return(
        <div onClick={onClick} className="character-card">
            <img src={character.image} alt={character.name}/>
            <h3>{character.name}</h3>
        </div>
    );
}

export default CharacterCard;