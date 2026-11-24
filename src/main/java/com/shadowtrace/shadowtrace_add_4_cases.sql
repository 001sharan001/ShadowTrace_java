-- Add four complete mystery cases to the currently selected ShadowTrace database.
-- Safe to run more than once: case titles, suspect names, and clue titles are checked before insertion.
USE shadowtrace_db;
START TRANSACTION;

INSERT INTO cases (title, description, difficulty, status, correct_answer)
SELECT 'The Vanishing Violin', 'A rare violin vanished from a locked rehearsal room shortly before a concert. The room was unlocked only for the conductor, the instrument technician, and the lead violinist.', 'EASY', 'OPEN', 'Mara Chen'
WHERE NOT EXISTS (SELECT 1 FROM cases WHERE title = 'The Vanishing Violin');

SET @case_id = (SELECT case_id FROM cases WHERE title = 'The Vanishing Violin' ORDER BY case_id LIMIT 1);
INSERT INTO suspects (case_id, name, age, occupation, description, is_guilty)
SELECT @case_id, d.name, d.age, d.occupation, d.description, d.is_guilty FROM (
  SELECT 'Mara Chen' name, 34 age, 'Lead violinist' occupation, 'She knew the instrument case and had a solo scheduled.' description, 1 is_guilty UNION ALL
  SELECT 'Owen Price', 46, 'Conductor', 'He was on stage reviewing the program during the final check.', 0 UNION ALL
  SELECT 'Lila Gomez', 29, 'Instrument technician', 'She had legitimate access while tuning the violin earlier that day.', 0
) d WHERE NOT EXISTS (SELECT 1 FROM suspects s WHERE s.case_id=@case_id AND s.name=d.name);

INSERT INTO clues (case_id, clue_title, clue_description, is_hidden)
SELECT @case_id, d.title, d.description, d.is_hidden FROM (
  SELECT 'Rosin on the latch' title, 'Fresh violin rosin was found on the inside latch, though the technician had wiped it clean after tuning.' description, 0 is_hidden UNION ALL
  SELECT 'Solo marked in pencil', 'The lead violinist''s part has a pencilled note: “take the old one before the encore.”', 0 UNION ALL
  SELECT 'Case weight', 'The instrument case weighed less when it was returned than when it was logged in.', 0
) d WHERE NOT EXISTS (SELECT 1 FROM clues c WHERE c.case_id=@case_id AND c.clue_title=d.title);

INSERT INTO cases (title, description, difficulty, status, correct_answer)
SELECT 'The Lighthouse Signal', 'During a storm, the harbor lighthouse sent an unauthorized signal that diverted a cargo vessel. The signal room was locked, and three staff members had access to the service corridor.', 'MEDIUM', 'OPEN', 'Elias Ward'
WHERE NOT EXISTS (SELECT 1 FROM cases WHERE title = 'The Lighthouse Signal');

SET @case_id = (SELECT case_id FROM cases WHERE title = 'The Lighthouse Signal' ORDER BY case_id LIMIT 1);
INSERT INTO suspects (case_id, name, age, occupation, description, is_guilty)
SELECT @case_id, d.name, d.age, d.occupation, d.description, d.is_guilty FROM (
  SELECT 'Elias Ward' name, 51 age, 'Harbor radio operator' occupation, 'He had access to the signal controls and knew the vessel route.' description, 1 is_guilty UNION ALL
  SELECT 'Nina Patel', 38, 'Lighthouse keeper', 'She was repairing the generator in the lower room.', 0 UNION ALL
  SELECT 'Tom Bell', 27, 'Dock messenger', 'He delivered a maintenance note shortly before the signal.', 0
) d WHERE NOT EXISTS (SELECT 1 FROM suspects s WHERE s.case_id=@case_id AND s.name=d.name);

INSERT INTO clues (case_id, clue_title, clue_description, is_hidden)
SELECT @case_id, d.title, d.description, d.is_hidden FROM (
  SELECT 'Dry control panel' title, 'The signal lever was dry despite rainwater on the corridor floor; someone used the indoor access route.' description, 0 is_hidden UNION ALL
  SELECT 'Copied route chart', 'A torn copy of the cargo vessel route was found in the radio operator''s desk.', 0 UNION ALL
  SELECT 'Generator log', 'The keeper''s repair log confirms the generator room was occupied during the signal.', 0
) d WHERE NOT EXISTS (SELECT 1 FROM clues c WHERE c.case_id=@case_id AND c.clue_title=d.title);

INSERT INTO cases (title, description, difficulty, status, correct_answer)
SELECT 'The Museum After Midnight', 'A priceless bronze compass disappeared from a display after the museum closed. No alarm was triggered, and only the curator, a guard, and a restoration apprentice remained inside.', 'MEDIUM', 'OPEN', 'Noah Reed'
WHERE NOT EXISTS (SELECT 1 FROM cases WHERE title = 'The Museum After Midnight');
SET @case_id = (SELECT case_id FROM cases WHERE title = 'The Museum After Midnight' ORDER BY case_id LIMIT 1);
INSERT INTO suspects (case_id, name, age, occupation, description, is_guilty)
SELECT @case_id, d.name, d.age, d.occupation, d.description, d.is_guilty FROM (
  SELECT 'Noah Reed' name, 24 age, 'Restoration apprentice' occupation, 'He had handled the compass during a scheduled cleaning and knew the display mechanism.' description, 1 is_guilty UNION ALL
  SELECT 'Ada Brooks', 57, 'Museum curator', 'She held the display keys and was preparing the next exhibition.', 0 UNION ALL
  SELECT 'Victor Hale', 42, 'Night guard', 'He monitored the galleries and reported no alarm activity.', 0
) d WHERE NOT EXISTS (SELECT 1 FROM suspects s WHERE s.case_id=@case_id AND s.name=d.name);
INSERT INTO clues (case_id, clue_title, clue_description, is_hidden)
SELECT @case_id, d.title, d.description, d.is_hidden FROM (
  SELECT 'Wax on the hinge' title, 'A small amount of fresh conservation wax was left on the display hinge.' description, 0 is_hidden UNION ALL
  SELECT 'Unsigned cleaning card', 'The cleaning card is unsigned, but its handwriting matches the apprentice''s workshop notes.', 0 UNION ALL
  SELECT 'Silent alarm test', 'The alarm was briefly disabled using the maintenance procedure known to restoration staff.', 0
) d WHERE NOT EXISTS (SELECT 1 FROM clues c WHERE c.case_id=@case_id AND c.clue_title=d.title);

INSERT INTO cases (title, description, difficulty, status, correct_answer)
SELECT 'The Midnight Train Ledger', 'A railway payroll ledger disappeared from a locked station office during the midnight shift. It contained records that could expose a long-running payment fraud.', 'HARD', 'OPEN', 'Clara Voss'
WHERE NOT EXISTS (SELECT 1 FROM cases WHERE title = 'The Midnight Train Ledger');
SET @case_id = (SELECT case_id FROM cases WHERE title = 'The Midnight Train Ledger' ORDER BY case_id LIMIT 1);
INSERT INTO suspects (case_id, name, age, occupation, description, is_guilty)
SELECT @case_id, d.name, d.age, d.occupation, d.description, d.is_guilty FROM (
  SELECT 'Clara Voss' name, 44 age, 'Payroll clerk' occupation, 'She prepared the ledger and could alter the payroll entries.' description, 1 is_guilty UNION ALL
  SELECT 'Peter Shaw', 36, 'Station master', 'He held the office master key and was checking the platform schedule.', 0 UNION ALL
  SELECT 'Imani Cole', 31, 'Night cleaner', 'She cleaned the corridor but says she never entered the office.', 0
) d WHERE NOT EXISTS (SELECT 1 FROM suspects s WHERE s.case_id=@case_id AND s.name=d.name);

INSERT INTO clues (case_id, clue_title, clue_description, is_hidden)
SELECT @case_id, d.title, d.description, d.is_hidden FROM (
  SELECT 'Carbon-paper stain' title, 'Blue carbon residue on the clerk''s cuff matches the ledger sheets, which were handled after the office was supposedly locked.' description, 0 is_hidden UNION ALL
  SELECT 'Key register', 'The master key remained signed in with the station master throughout the shift.', 0 UNION ALL
  SELECT 'Altered totals', 'The ledger''s final page contains corrections in the same narrow handwriting as the payroll clerk''s notes.', 0
) d WHERE NOT EXISTS (SELECT 1 FROM clues c WHERE c.case_id=@case_id AND c.clue_title=d.title);

COMMIT;

-- Confirm the case count and titles after running the script.
SELECT case_id, title, difficulty, status FROM cases ORDER BY case_id;
