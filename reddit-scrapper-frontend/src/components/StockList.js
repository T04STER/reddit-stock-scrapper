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
      .catch((err) =>
        setStockList([
          {
            id: 1,
            symbol: 'IPO',
            open: 31.16,
            high: 31.16,
            low: 30.32,
            price: 30.41,
            volume: 38199,
            previousClose: 31.57,
            change: -1.16,
            changePercent: '-3.6744%',
          },
          {
            id: 2,
            symbol: 'COIN',
            open: 61.69,
            high: 62.89,
            low: 52.63,
            price: 52.93,
            volume: 26875839,
            previousClose: 67.07,
            change: -14.14,
            changePercent: '-21.0825%',
          },
          {
            id: 3,
            symbol: 'MSFT',
            open: 259.86,
            high: 259.88,
            low: 249.57,
            price: 251.9,
            volume: 39347957,
            previousClose: 258.83,
            change: -6.93,
            changePercent: '-2.6774%',
          },
          {
            id: 4,
            symbol: 'GOOGL',
            open: 106.6,
            high: 107.2,
            low: 104.07,
            price: 105.02,
            volume: 47990963,
            previousClose: 107.51,
            change: -2.49,
            changePercent: '-2.3161%',
          },
          {
            id: 5,
            symbol: 'WMT',
            open: 121.13,
            high: 122.69,
            low: 120.06,
            price: 121.98,
            volume: 31797182,
            previousClose: 132.02,
            change: -10.04,
            changePercent: '-7.6049%',
          },
          {
            id: 6,
            symbol: 'PYPL',
            open: 80.0304,
            high: 80.22,
            low: 76.71,
            price: 77.04,
            volume: 13245434,
            previousClose: 81.65,
            change: -4.61,
            changePercent: '-5.6461%',
          },
          {
            id: 7,
            symbol: 'EPS',
            open: 42.335,
            high: 42.335,
            low: 41.95,
            price: 42.01,
            volume: 552648,
            previousClose: 42.55,
            change: -0.54,
            changePercent: '-1.2691%',
          },
          {
            id: 8,
            symbol: 'CMG',
            open: 1318.16,
            high: 1327.1,
            low: 1291.63,
            price: 1316.43,
            volume: 503003,
            previousClose: 1328.87,
            change: -12.44,
            changePercent: '-0.9361%',
          },
          {
            id: 9,
            symbol: 'SPOT',
            open: 105.94,
            high: 107.3499,
            low: 103.395,
            price: 103.97,
            volume: 2752557,
            previousClose: 110.42,
            change: -6.45,
            changePercent: '-5.8413%',
          },
        ])
      );
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
