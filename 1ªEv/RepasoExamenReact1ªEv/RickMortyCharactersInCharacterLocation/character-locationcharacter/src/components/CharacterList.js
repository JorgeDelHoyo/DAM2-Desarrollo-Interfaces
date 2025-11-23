import CharacterCard from "./CharacterCard";
import './CharacterList.css';

function CharacterList({characters, onClick}) {
    if(characters.length === 0) return <p>No characters found</p>
    return(
        <div className="character-list">
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