DROP TABLE IF EXISTS developers CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE developers (
                            developer_id    BIGSERIAL PRIMARY KEY,
                            company_code  VARCHAR(255) NOT NULL,
                            company_name  VARCHAR(255) NOT NULL,
                            email         VARCHAR(255),
                            phone         VARCHAR(255),
                            website       VARCHAR(255) NOT NULL,
                            CONSTRAINT UK_7g49xa70lkfq28inwu57m3fam UNIQUE (company_code),
                            CONSTRAINT UK_9k7mrq5efbudcfvvnpoeqe0a3 UNIQUE (website),
                            CONSTRAINT UK_e4sqx3he4mppxr5vxo0dpuxfc UNIQUE (company_name)
);

CREATE TABLE users (
                       user_id      BIGSERIAL PRIMARY KEY,
                       birth_date   DATE,
                       email        VARCHAR(40) NOT NULL,
                       first_name   VARCHAR(40) NOT NULL,
                       last_name    VARCHAR(40) NOT NULL,
                       login        VARCHAR(40) NOT NULL,
                       password     VARCHAR(255) NOT NULL,
                       phone_number VARCHAR(20),
                       CONSTRAINT UK_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
                       CONSTRAINT UK_ow0gan20590jrb00upg3va2fn UNIQUE (login)
);


insert into users(email, first_name, last_name, login, password)
values ('melnikov@mail.ru', 'aleksey', 'aleksey', 'aleksey@mail.ru', '132855669868590f3e1e9b22f3504d6ba78fda16ae0d003b834c43a47a3c804c');
insert into users(email, first_name, last_name, login, password)
values ('alex123@mail.ru', 'alex', 'pavlovich', 'alex1232@mail.ru', '132855669868590f3e1e9b22f3504d6ba78fda16ae0d003b834c43a47a3c804c');
insert into users(email, first_name, last_name, login, password, birth_date)
values ('alex@mail.ru', 'alex', 'pavlovich', 'alex@mail.ru', '132855669868590f3e1e9b22f3504d6ba78fda16ae0d003b834c43a47a3c804c', '2001-08-18');




-- Объединенный SQL-запрос для добавления данных о девелоперских компаниях
insert into developers (developer_id, company_code, company_name, email, phone, website)
values
    (1, 'DEVELOP1', 'UrbanBuilders', 'urban.builders@mail.com', '+79851234567', 'www.urbanbuilders.com'),
    (2, 'DEVELOP2', 'EcoHomes', 'ecohomes@mail.com', '+79852345678', 'www.ecohomes.com'),
    (3, 'DEVELOP3', 'SkyScrapers Ltd', 'skyscrapers@mail.com', '+79853456789', 'www.skyscrapersltd.com'),
    (4, 'DEVELOP4', 'GreenSpaces Developers', 'greenspaces@mail.com', '+79854567890', 'www.greenspacesdevelopers.com'),
    (5, 'DEVELOP5', 'FutureEstates', 'futureestates@mail.com', '+79855678901', 'www.futureestates.com'),
    (6, 'DEVELOP6', 'SmartBuilders', 'smart.builders@mail.com', '+79856789012', 'www.smartbuilders.com'),
    (7, 'DEVELOP7', 'InnovativeHomes', 'innovative.homes@mail.com', '+79857890123', 'www.innovativehomes.com'),
    (8, 'DEVELOP8', 'CityScape Developers', 'cityscape@mail.com', '+79858901234', 'www.cityscapedevelopers.com'),
    (9, 'DEVELOP9', 'ModernLiving Developers', 'modernliving@mail.com', '+79859012345', 'www.modernlivingdevelopers.com'),
    (10, 'DEVELOP10', 'GreenTech Estates', 'greentech@mail.com', '+79850123456', 'www.greentechestates.com'),
    (11, 'DEVELOP11', 'BlueSky Builders', 'bluesky@mail.com', '+79851234567', 'www.blueskybuilders.com'),
    (12, 'DEVELOP12', 'GoldenGate Properties', 'goldengate@mail.com', '+79852345678', 'www.goldengateproperties.com'),
    (13, 'DEVELOP13', 'Sunrise Developments', 'sunrise@mail.com', '+79853456789', 'www.sunrisedevelopments.com'),
    (14, 'DEVELOP14', 'CityHorizon Homes', 'cityhorizon@mail.com', '+79854567890', 'www.cityhorizonhomes.com'),
    (15, 'DEVELOP15', 'TechSavvy Builders', 'techsavvy@mail.com', '+79855678901', 'www.techsavvybuilders.com'),
    (16, 'DEVELOP16', 'Evergreen Constructions', 'evergreen@mail.com', '+79856789012', 'www.evergreenconstructions.com'),
    (17, 'DEVELOP17', 'GrandView Estates', 'grandview@mail.com', '+79857890123', 'www.grandviewestates.com'),
    (18, 'DEVELOP18', 'Unity Urban Developers', 'unityurban@mail.com', '+79858901234', 'www.unityurbandevelopers.com'),
    (19, 'DEVELOP19', 'Harmony Homes Ltd', 'harmonyhomes@mail.com', '+79859012345', 'www.harmonyhomesltd.com'),
    (20, 'DEVELOP20', 'Skyline Innovations', 'skyline@mail.com', '+79850123456', 'www.skylineinnovations.com'),
    (21, 'DEVELOP21', 'GreenField Builders', 'greenfield@mail.com', '+79851234567', 'www.greenfieldbuilders.com'),
    (22, 'DEVELOP22', 'UrbanScape Developers', 'urbanscape@mail.com', '+79852345678', 'www.urbanscapedevelopers.com'),
    (23, 'DEVELOP23', 'NatureVista Homes', 'naturevista@mail.com', '+79853456789', 'www.naturevistahomes.com'),
    (24, 'DEVELOP24', 'Infinite Properties', 'infinite@mail.com', '+79854567890', 'www.infiniteproperties.com'),
    (25, 'DEVELOP25', 'SkyRise Constructions', 'skyrise@mail.com', '+79855678901', 'www.skyriseconstructions.com'),
    (26, 'DEVELOP26', 'GoldenMeadow Builders', 'goldenmeadow@mail.com', '+79856789012', 'www.goldenmeadowbuilders.com'),
    (27, 'DEVELOP27', 'Prestige Homes Ltd', 'prestigehomes@mail.com', '+79857890123', 'www.prestigehomesltd.com'),
    (28, 'DEVELOP28', 'EcoVille Developers', 'ecoville@mail.com', '+79858901234', 'www.ecovilledevelopers.com'),
    (29, 'DEVELOP29', 'UrbanHarbor Estates', 'urbanharbor@mail.com', '+79859012345', 'www.urbanharborestates.com'),
    (30, 'DEVELOP30', 'Sunset Views Properties', 'sunsetviews@mail.com', '+79850123456', 'www.sunsetviewsproperties.com'),
    (31, 'DEVELOP31', 'GreenOasis Homes', 'greenoasis@mail.com', '+79851234567', 'www.greenoasishomes.com'),
    (32, 'DEVELOP32', 'CitySquare Builders', 'citysquare@mail.com', '+79852345678', 'www.citysquarebuilders.com'),
    (33, 'DEVELOP33', 'Everlast Developments', 'everlast@mail.com', '+79853456789', 'www.everlastdevelopments.com'),
    (34, 'DEVELOP34', 'BlueHorizon Properties', 'bluehorizon@mail.com', '+79854567890', 'www.bluehorizonproperties.com'),
    (35, 'DEVELOP35', 'TechVista Constructions', 'techvista@mail.com', '+79855678901', 'www.techvistaconstructions.com'),
    (36, 'DEVELOP36', 'EcoLiving Builders', 'ecoliving@mail.com', '+79856789012', 'www.ecolivingbuilders.com'),
    (37, 'DEVELOP37', 'ModernScape Developers', 'modernscape@mail.com', '+79857890123', 'www.modernscapedevelopers.com'),
    (38, 'DEVELOP38', 'NatureScape Homes', 'naturescape@mail.com', '+79858901234', 'www.naturescapehomes.com'),
    (39, 'DEVELOP39', 'CityBreeze Properties', 'citybreeze@mail.com', '+79859012345', 'www.citybreezeproperties.com'),
    (40, 'DEVELOP40', 'SkyVista Innovations', 'skyvista@mail.com', '+79850123456', 'www.skyvistainnovations.com');
