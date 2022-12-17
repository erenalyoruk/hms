# Hospital Management System

JavaFX Desktop application for managing appointments.

## Requirements

- JDK 17
- JAVA_HOME should be set for gradle.

## Collaboration

Change `create` to `update` in `resources/hibernate.cfg.xml` if you do not want to create fresh database every launch.

```xml
<property name="hbm2ddl.auto">create</property>
```

### Steps to contribute

1. Create new branch.
2. Make changes.
3. Test it.
4. Run `spotlessApply` for code formatting.
5. Make pull request. Do not merge directly.
