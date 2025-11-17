import './CharacterCard.css';

function CharacterCard({character, openDetails}){
    return(
        <div onClick={openDetails} className="character-card">
            <img src={character.image}/>
            <h3>{character.name}</h3>
        </div>
    );
}

export default CharacterCard;