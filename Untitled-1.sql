-- drop table institution_input_file_config;


-- drop table input_file_model;
-- drop table reg_ex_config_model;

-- drop table institution;








INSERT INTO
  "CCUPS_DB"."public"."institution" (code, description)
VALUES
  (
    '0016',
    'SMART'
  );
INSERT INTO
  "CCUPS_DB"."public"."institution" (code, description)
VALUES
  (
    '0012',
    'MERALCO'
  );
INSERT INTO
  "CCUPS_DB"."public"."data_type_model" (description, regexpattern)
VALUES
  (
    'Alpha & Special Characters',
    
    '[a-zA-Z0-9\s\_\@\$\&\(\)\-\[\]\;\:\,\.\/\|\\]'
  );

