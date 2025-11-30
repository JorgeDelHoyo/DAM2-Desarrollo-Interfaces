function CharacterDetail({character, onClose}) {
    return(
        <div className="modal-overlay">
            <div className="modal-content" onClick={(e) => e.stopPropagation()}>
                <button onClick={onClose} className="close-button">X</button>
                <div>
                    <img src={character.image} alt={character.name}/>
                    <h3>{character.name}</h3>
                    <p>{character.species}</p>
                </div>
            </div>
        </div>
    );
}

export default CharacterDetail;