function EpisodeList({episodes, onClick}) {
    if(episodes.length === 0) return <p>No episodes found</p>

    return(
        <div className="list-container">
            {episodes.map((episode) => (
                <h3 key={episode.id}
                    onClick={() => onClick(episode)}
                    className="list-item">
                    {episode.name}</h3>
            ))}
        </div>
    );
}

export default EpisodeList;