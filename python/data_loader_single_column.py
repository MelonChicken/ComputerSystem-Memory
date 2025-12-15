import pandas as pd
from huggingface_hub import hf_hub_download

REPO_ID = "Rif-SQL/time-series-uk-retail-supermarket-price-data"
OUT = "uk_prices_8000_key_price.csv"

parquet_path = hf_hub_download(
    repo_id=REPO_ID,
    repo_type="dataset",
    filename="base_retail_gb_snappy.parquet"
)

df = pd.read_parquet(parquet_path).head(8000).reset_index(drop=True)

df = df[["price_gbp"]].copy()
df.insert(0, "key", df.index)

df.to_csv(OUT, index=False)
print("saved:", OUT, "rows:", len(df))