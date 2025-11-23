import './CharacterCard.css';

function CharacterCard({character, onClick}) {
    return(
        <div onClick={onClick} className='character-card'>
            <img src={character.image} alt={character.name} /> 
            <div className='card-info'>
                <h3>{character.name}</h3>
            </div>
        </div>
    );
}
export default CharacterCard;