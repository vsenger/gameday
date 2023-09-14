import json
import boto3

bedrock_client = boto3.client(
    'bedrock', 'us-east-1', endpoint_url='https://bedrock.us-east-1.amazonaws.com')

body = json.dumps({"inputText": "Where I can find home made bread in Seattle?"})
modelId = 'amazon.titan-tg1-large'
accept = 'application/json'
contentType = 'application/json'

response = bedrock_client.invoke_model(
    body=body, modelId=modelId, accept=accept, contentType=contentType)
response_body = json.loads(response.get('body').read())

print(response_body.get('results')[0].get('outputText'))
