import ai21

# J2 Ultra
response = ai21.Completion.execute(
  destination=ai21.BedrockDestination(model_id=ai21.BedrockModelID.J2_JUMBO_INSTRUCT),
  prompt="explain Java programming language to teenagers",
  numResults=1,
  maxTokens=100,
  temperature=0.7
)

print(response)
