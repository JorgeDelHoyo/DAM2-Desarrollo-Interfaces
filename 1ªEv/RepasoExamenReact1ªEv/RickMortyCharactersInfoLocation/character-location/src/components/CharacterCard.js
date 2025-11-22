import './CharacterCard.css';

function CharacterCard({character, onClick}) {
    return(
        <div onClick={onClick} className="character-card">
            <img src={character.image}/>
            <p>{character.name}</p>
        </div>
    );
}

export default CharacterCard;