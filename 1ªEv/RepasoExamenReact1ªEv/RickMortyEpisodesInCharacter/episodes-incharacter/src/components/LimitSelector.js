function LimitSelector({limit, onChangeLimit}) {
    return(
        <div className="limit-selector">
            <label>Mostrar:</label>
            <select value={limit}
                    onChange={(e) => onChangeLimit(Number(e.target.value))}
                    className="limit-select">
                <option value={9999}>Todos</option>
                <option value={5}>5 elementos</option>
                <option value={10}>10 elementos</option>
                <option value={20}>20 elementos</option>
            </select>
        </div>
    );
}

export default LimitSelector;