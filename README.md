# NASA APOD (Astronomy Picture of the Day) API validation

**APOD requirements from https://api.nasa.gov/**

1. GET https://api.nasa.gov/planetary/apod
2. Query Parameters table:

Parameter | Type| Default| Description|
----------| ----| -------| -----------|
date | YYYY-MM-DD | today | The date of the APOD image to retrieve |
hd | bool | False | Retrieve the URL for the high resolution image |
api_key | string | DEMO_KEY | api.nasa.gov key for expanded usage |
3. *Concept_tags* are now disabled in this service.
4. Optional return parameter *copyright* is returned if the image is not public domain.

**NASA api_key requirements from https://api.nasa.gov/**
1. Rate limits may vary by service (default 1000 per hour).
2. *X-RateLimit-Limit* and *X-RateLimit-Remaining HTTP* headers that are returned on every API response.
3. In documentation examples, the special DEMO_KEY api key is used. This API key can be used for initially exploring APIs prior to signing up.
4. DEMO_KEY hourly limit: 30
5. DEMO_KEY day limit: 50

# NASA APOD API Test Cases
**Positive**
1. GET with all parameters: *api_key=generated*  | *hd=false* | *date=yesterday*
2. GET without parameters to verify how parameters default values applies.
3. GET with single parameter: *api_key=generated* OR *demo* | *hd=false* | *date=days ago* OR *month ago* OR *year ago* OR *boundary value for date in the past*
4. GET to check requirements for *copyright* field appearing.
5. GET with *Concept_tags* parameter to verify how it disabled.
6. GET with extra spaces at the begining of param and at the end to verify how trim() value works on service side.

**Negative**
1. GET with wrong *api_key*
2. GET with single parameter of unexpected format: *date* | *hd*
3. GET with future *date* in expected format.
4. Unexpected request type with query parameters.
5. HTTP request instead of HTTPS.

**API_KEY specific tests**
1. GET with *DEMO_KEY* and check that *X-RateLimit-Limit* and *X-RateLimit-Remaining HTTP* headers values changed.
2. Check daily & hourly limits for *DEMO_KEY* per IP address.
3. Check daily & hourly limits for generated *api_key*
4. Check restore key functionality if service have it even for internal use by support.

**Performance  tests**
1. Performance  test on low load with positive requests with random parameters.
2. Capacity test depends on release, infrustructure change and etc.

# NASA APOD API Known Issues
1. Default *api_key* doesn't applies for request without *api_key* parameter -> requires discussion and documentation update. Not clear for now is *DEMO_KEY* applies by default like *today* value for *date* or it CAN BE used by default without additional registration.
2. Service ignores *hd* param value. *hd* always true.
3. Service doesn't apply trim of extra spaces at the beginning and at the end of value.
4. Service doesn't ignore unexpected quesry params.
5. Error for unexpected quesry params contains undocumented params and doesn't contains some documented params.
6. Two different types of *400 Bad Request* error responses: GET with unexpected query param -> type 1 | GET with HTTP scheme -> type 2.
7. *405 Method Not Allowed* error response is in HTML format for unexpected request types (POST, DELETE and etc).
8. Service can returns video instead of image (*date=2020-12-16*). In this case response body doesn't contains *hdurl* that appears by default for image (see known issue #2).
9. Different error type for *date=tomorrow* and *date=date after tomorrow*. That is not described in the requirements.

# NASA APOD API Test Framework

**Implementation**
1. Maven Java 8 project.
2. Spring Boot 2.3.6.RELEASE as parent dependency.
3. TestNg 7.3.0 as xUnit framework.
4. Rest-Assured for HTTP requests.
5. Maven surfire plugin to simplify execution from command line.

**Nuances of tests implementations**
1. Not null check only for response body fields which values we don't know in advance -> Backend access required to get or create test data to improve validation.
2. Avoiding *hdurl* response field validation just to make demo project stable, because this field is affected by known issues #2 & #8.
3. Avoiding *copyright* field validation because it appearens unpredictable when no read/write access to backend data. See requirement #3.
4. Different models for Forbiddern & Bad Request error responses. See known issue #6.

**How To Run**
1. Import to IDE & execute `regression_testng.xml`.
2. Navigate to project root directory & execute `mvn clean test` command.


