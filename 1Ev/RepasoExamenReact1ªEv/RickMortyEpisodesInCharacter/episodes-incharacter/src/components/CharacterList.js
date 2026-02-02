import CharacterCard from "./CharacterCard";

function CharacterList({characters, onClick}) {
    if(characters.length === 0) return <p>Characters not found</p>

    return(
        <div className="character-grid">
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