CREATE SCHEMA IF NOT EXISTS CookEasyDatabase;
USE CookEasyDatabase;

DROP TABLE IF EXISTS SavedRecipes;
DROP TABLE IF EXISTS Ratings;
DROP TABLE IF EXISTS Recommendation;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS RecipeIngredients;
DROP TABLE IF EXISTS Ingredients;
DROP TABLE IF EXISTS KeywordInRecipe;
DROP TABLE IF EXISTS Keywords;
DROP TABLE IF EXISTS Recipes;

CREATE TABLE Keywords (
	KeywordName VARCHAR(255),
    CONSTRAINT pk_Keywords_KeywordName	# changed, use KeywordName as primary key
		PRIMARY KEY (KeywordName)
);

CREATE TABLE Ingredients (
    IngredientName VARCHAR(255),
    CONSTRAINT pk_Ingredients_IngredientName	# changed, use IngredientName as primary key
		PRIMARY KEY (IngredientName)
);

CREATE TABLE Recipes (
	RecipeID INT,
    RecipeName VARCHAR(255),
    PrepTime VARCHAR(255),    #NEED TO UNDERSTAND THIS BETTER
    CookTime VARCHAR(255),   #NEED TO UNDERSTAND THIS BETTER
    DatePublished DATE,
    Description LONGTEXT,	# change to LONGTEXT because the text is too long		by LinLi
    Images LONGTEXT,		# change to LONGTEXT because the text is too long		by LinLi
    Calories DECIMAL (10, 2),
    FatContent DECIMAL (10, 2),
    SaturatedFatContent DECIMAL (10, 2),
    CholestrolContent DECIMAL (10, 2),
    SodiumContent DECIMAL (10, 2),
    CarbohydrateContent DECIMAL (10, 2),
    FiberContent DECIMAL (10, 2),
    SugarContent DECIMAL (10, 2),
    ProteinContent DECIMAL (10, 2),
    RecipeServings DECIMAL (10, 2),
    RecipeInstructions LONGTEXT,
    CONSTRAINT pk_Recipes_RecipeID
		PRIMARY KEY(RecipeID)
);

CREATE TABLE KeywordInRecipe (
	KeywordRecipeID INT AUTO_INCREMENT,
    KeywordName VARCHAR(255),
    RecipeID INT,
    CONSTRAINT pk_KeywordInRecipe_KeywordRecipeID
		PRIMARY KEY (KeywordRecipeID),
	CONSTRAINT fk_KeywordInRecipe_KeywordName
		FOREIGN KEY (KeywordName)
        REFERENCES Keywords(KeywordName)
        ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_KeywordInRecipe_RecipeID
		FOREIGN KEY (RecipeID)
        REFERENCES Recipes(RecipeID)
        ON UPDATE CASCADE ON DELETE CASCADE
    );
    
CREATE TABLE RecipeIngredients (
	RecipeIngredientID INT AUTO_INCREMENT,
    RecipeID INT,
    IngredientName VARCHAR(255),
    Quantity VARCHAR(255),		# changed
    CONSTRAINT pk_RecipeIngredients_RecipeIngredientID
		PRIMARY KEY (RecipeIngredientID),
	CONSTRAINT fk_RecipeIngredients_RecipeID
		FOREIGN KEY (RecipeID)
        REFERENCES Recipes(RecipeID)
        ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_RecipeIngredients_IngredientName
		FOREIGN KEY (IngredientName)
        REFERENCES Ingredients(IngredientName)
        ON UPDATE CASCADE ON DELETE CASCADE
    );

CREATE TABLE Users (
    UserName VARCHAR(255),
    FIrstName VARCHAR(255),
    LastName VARCHAR(255),
    Email VARCHAR(255),
    CONSTRAINT pk_Users_UserName
		PRIMARY KEY(UserName)
);

CREATE TABLE SavedRecipes (
	SavedRecipeID INT AUTO_INCREMENT,
    RecipeId INT,
    UserName VARCHAR(255),
    RecommendedByCookEasy bool,
    CONSTRAINT pk_SavedRecipes_SavedRecipeID
		PRIMARY KEY(SavedRecipeID),
	CONSTRAINT fk_SavedRecipes_RecipeID
		FOREIGN KEY(RecipeID)
        REFERENCES Recipes(RecipeID)
        ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_SavedRecipes_UserName
		FOREIGN KEY(UserName)
        REFERENCES Users(UserName)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Ratings (
	RatingId INT AUTO_INCREMENT,
    RecipeId INT,
    UserName VARCHAR(255),
    Difficulty DECIMAL (3, 1),
    Cost DECIMAL (3, 1),
    Taste DECIMAL (3, 1),
    Time DECIMAL (3, 1),
    CONSTRAINT pk_Ratings_RatingID
		PRIMARY KEY(RatingID),
	CONSTRAINT fk_Ratings_RecipeID
		FOREIGN KEY(RecipeID)
        REFERENCES Recipes(RecipeID)
        ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Ratings_UserName
		FOREIGN KEY(UserName)
        REFERENCES Users(UserName)
        ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Recommendation (
	RecommendationId INT,
    RecipeId INT,
    UserName VARCHAR(255),
    CONSTRAINT pk_Recommendation_RecommendationID
		PRIMARY KEY (RecommendationID),
	CONSTRAINT fk_Recommendation_RecipeID
		FOREIGN KEY (RecipeID)
        REFERENCES Recipes(RecipeID)
        ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Recommendation_UserName
		FOREIGN KEY (UserName)
        REFERENCES Users(UserName)
        ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Reviews (
	ReviewId INT,
    UserName VARCHAR(255),
    RecipeId INT,
    Content LONGTEXT,
    Created TIMESTAMP,
    CONSTRAINT pk_Reviews_ReviewID
		PRIMARY KEY (ReviewID),
	CONSTRAINT fk_Reviews_UserName
		FOREIGN KEY (UserName)
        REFERENCES Users(UserName)
        ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_Reviews_RecipeID
		FOREIGN KEY (RecipeID)
        REFERENCES Recipes(RecipeID)
        ON UPDATE CASCADE ON DELETE CASCADE
);


# load data for recipes
LOAD DATA LOCAL INFILE '/Users/colinnordquist/Downloads//CookEasy/recipes_0.csv'
	INTO TABLE Recipes
	FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES
	(RecipeID, RecipeName, @dummy, @dummy, CookTime, PrepTime, @dummy, @val1, 
    Description, Images, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, Calories, 
    FatContent, SaturatedFatContent, CholestrolContent, SodiumContent, CarbohydrateContent, 
    FiberContent, SugarContent, ProteinContent, @valServ, @dummy, RecipeInstructions)
    SET DatePublished = substr(@val1, 1, 10);
    
# load data for keywords
LOAD DATA LOCAL INFILE '/Users/colinnordquist/Downloads/CookEasy/keywords_0.csv'
	INTO TABLE Keywords
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\n'
    (KeywordName);

# load data for ingredients
LOAD DATA LOCAL INFILE '/Users/colinnordquist/Downloads/CookEasy/ingredients_0.csv'
	INTO TABLE Ingredients
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\n'
    (IngredientName);

# load data for recipe_keywords
LOAD DATA LOCAL INFILE '/Users/colinnordquist/Downloads/CookEasy/recipe_keywords_0.csv'
	INTO TABLE KeywordInRecipe
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\n'
    (RecipeID, KeywordName);

# load data for recipe_ingredients
# recipe_ingredients_0 contains lots of data, if cannot load data directly from recipe_ingredients_0, try 
# recipe_ingredients_0_0 to recipe_ingredients_0_7 , where I split the data into 7 smaller files
LOAD DATA LOCAL INFILE '/Users/colinnordquist/Downloads/CookEasy/recipe_ingredients_0.csv'
	INTO TABLE RecipeIngredients
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES
    (RecipeID, Quantity, IngredientName);
    
LOAD DATA LOCAL INFILE '/Users/colinnordquist/Downloads/CookEasy/GeneratedData/CookEasy_data - Users.csv'
	INTO TABLE Users
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES
    (UserName, FirstName, LastName, Email);

LOAD DATA LOCAL INFILE '/Users/colinnordquist/Downloads/CookEasy/GeneratedData/CookEasy_data - Recommendations.csv'
	INTO TABLE Recommendation
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
	IGNORE 1 LINES
    (RecommendationId, RecipeId, UserName);

LOAD DATA LOCAL INFILE '/Users/colinnordquist/Downloads/CookEasy/GeneratedData/CookEasy_data - Reviews.csv'
	INTO TABLE Reviews
	FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES
    (ReviewId, UserName, RecipeId, Content, Created);

LOAD DATA LOCAL INFILE '/Users/colinnordquist/Downloads/CookEasy/GeneratedData/CookEasy_data - Ratings.csv'
	INTO TABLE Ratings
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES
    (RecipeId, UserName, Difficulty, Cost, Taste, Time);

LOAD DATA LOCAL INFILE '/Users/colinnordquist/Downloads/CookEasy/GeneratedData/CookEasy_data - SavedRecipes.csv'
	INTO TABLE SavedRecipes
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES
    (SavedRecipeId, RecipeId, UserName, RecommendedByCookEasy);
