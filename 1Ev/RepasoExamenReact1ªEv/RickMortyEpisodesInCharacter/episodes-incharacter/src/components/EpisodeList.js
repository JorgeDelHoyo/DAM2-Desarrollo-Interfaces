function EpisodeList({episodes}) {
    if(episodes.length === 0) return <p>Episodes not found</p>

    return(
        <div className="list-container">
            {episodes.map((episode) => (
                <h3 key={episode.id} className="list-item">{episode.name}</h3>
            ))}
        </div>
    );
}

export default EpisodeList;