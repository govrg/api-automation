Rate Limit
You can check your current rate limit status at any time using the Rate Limit API described below.
Note: Accessing this endpoint does not count against your REST API rate limit.

curl -i https://api.github.com/rate_limit

{
  "resources": {
    "core": {
      "limit": 60,
      "remaining": 0,
      "reset": 1554886238
    },
    "search": {
      "limit": 10,
      "remaining": 9,
      "reset": 1554886256
    },
    "graphql": {
      "limit": 0,
      "remaining": 0,
      "reset": 1554889824
    },
    "integration_manifest": {
      "limit": 5000,
      "remaining": 5000,
      "reset": 1554889824
    }
  },
  "rate": {
    "limit": 60,
    "remaining": 0,
    "reset": 1554886238
  }
}