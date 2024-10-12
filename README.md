# desafiodnapanvel


curl para pegar o numero de mutantes e não mutantes e a comparação
curl --location 'http://35.188.0.123:8080/status' \
--header 'Content-Type: application/json' \
--data ''





curl para cadastrar um DNA
curl --location 'http://35.188.0.123:8080/mutant' \
--header 'Content-Type: application/json' \
--data '{
    "dna": [
        "ATGCGA",
                "CAGTGC",
                "TTATCT",
                "AGACGG",
                "CCTCTA",
                "TCACTG"  
    ]
}'