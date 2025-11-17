import CharacterCard from "./CharacterCard";
import './CharacterList.css';

function CharacterList({characters, openDetails}){
    if(characters.length === 0){
        return <div className="no-characters-found">Characters not found</div>
    }else{
        return(
            <div className="character-list">
                {characters.map((character) => (
                    <CharacterCard
                    key={character.id}
                    character={character}
                    onClick={() => openDetails(character)}
                    />
                ))}
            </div>
        );
    }
}

export default CharacterList;