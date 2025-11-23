import './CharacterList.css';
import CharacterCard from './CharacterCard';

function CharacterList({characters, onClick}) {
    if(characters.length === 0) return <p className='no-found'>Characters not found</p>
    return(
        <div className='character-list'>
            {characters.map((character) => (
                <CharacterCard
                    key={character.id}
                    character={character}
                    onClick={() => onClick(character)}
                />
            ))}
        </div>
    );
}

export default CharacterList;