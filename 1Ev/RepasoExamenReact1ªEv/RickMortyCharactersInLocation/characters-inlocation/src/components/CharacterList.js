import CharacterCard from "./CharacterCard";

function CharacterList({characters, onClick}) {
    if(characters.length === 0) return <p>No characters found</p>

    return(
        <div className="character-grid">
            {characters.map((character) => (
                <CharacterCard
                    key={character.key}
                    character={character}
                    onClick={() => onClick(character)}
                />
            ))}
        </div>
    );
}

export default CharacterList;