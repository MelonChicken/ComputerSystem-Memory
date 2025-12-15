import os
import pandas as pd
import pyarrow
import fsspec
import huggingface_hub

SRC = "hf://datasets/Rif-SQL/time-series-uk-retail-supermarket-price-data/base_retail_gb_snappy.parquet"
OUT_DIR = "data"
OUT_PATH = os.path.join(OUT_DIR, "sample_data.csv")

os.makedirs(OUT_DIR, exist_ok=True)

df = pd.read_parquet(SRC)

df = df.head(8000) # 8000개만 추출
df.to_csv(OUT_PATH, index=True)

print("saved:", OUT_PATH, "shape:", df.shape)
