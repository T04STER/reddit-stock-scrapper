import React, {useState} from 'react';
import {AiOutlineDown, AiOutlineUp} from 'react-icons/ai';

function StockListElement(props) {
  const stock = props.stock;
  const growing = stock.change > 0;
  const [isDown, setDown] = useState(false);

  function handleClick() {
    setDown(!isDown);
  }

  return (
    <div>
      <div
        className={
          isDown ? 'StockListElement down' : 'StockListElement'
        }
      >
        <div>{stock.id}</div>
        <div className="symbol">{stock.symbol}</div>
        <div className="price"> {stock.price}</div>
        <div className={growing ? 'change-green' : 'change-red'}>
          {stock.change}({growing ? '+' : ''}
          {stock.changePercent})
        </div>
        <div>
          <button onClick={handleClick}>
            {isDown ? <AiOutlineUp /> : <AiOutlineDown />}
          </button>
        </div>
      </div>
      {isDown ? (
        <div className="details">
          <div className="grid">
            <div>Previous close {stock.previousClose}</div>
            <div>Open {stock.open}</div>
            <div>High {stock.high}</div>
            <div>Volume {stock.volume}</div>
            <div>Low {stock.low}</div>
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
}

export default StockListElement;
