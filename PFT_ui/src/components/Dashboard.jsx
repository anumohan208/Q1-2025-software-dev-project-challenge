import { useState, useEffect } from "react";
import axios from "axios";
import "./Dashboard.css";

export default function Dashboard() {
  const [summary, setSummary] = useState({ income: 0, expenses: 0 });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios.get("http://localhost:8080/api/summary")
      .then((response) => {
        setSummary({ income: response.data.income, expenses: response.data.expenses });
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  }, []);

  const netBalance = summary.income - summary.expenses;

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div className="dashboard-container">
      <div className="tile-wrapper income-tile">
        <div className="tile">
          <div className="tile-content">
            <h2 className="tile-title">Total Income</h2>
            <p className="tile-value">${summary.income}</p>
          </div>
        </div>
      </div>

      <div className="tile-wrapper expense-tile">
        <div className="tile">
          <div className="tile-content">
            <h2 className="tile-title">Total Expenses</h2>
            <p className="tile-value">${summary.expenses}</p>
          </div>
        </div>
      </div>

      <div className={`tile-wrapper ${netBalance >= 0 ? "positive-balance" : "negative-balance"}`}>
        <div className="tile">
          <div className="tile-content">
            <h2 className="tile-title">Net Balance</h2>
            <p className="tile-value">${netBalance}</p>
          </div>
        </div>
      </div>
    </div>
  );
}
