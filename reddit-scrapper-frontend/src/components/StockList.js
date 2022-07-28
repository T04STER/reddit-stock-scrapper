import React, {useEffect, useState} from 'react';
import axios from 'axios';
import StockListElement from './StockListElement';

function StockList() {
  const [stockList, setStockList] = useState([]);

  useEffect(() => {
    axios
      .get('http://localhost:8080/api/v1/stocks')
      .then((res) => {
        setStockList(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="StockList">
      {stockList.map((stock) => (
        <StockListElement key={stock.id} stock={stock} />
      ))}
    </div>
  );
}

export default StockList;
